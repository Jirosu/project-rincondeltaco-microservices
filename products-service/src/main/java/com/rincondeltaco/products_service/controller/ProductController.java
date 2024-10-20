package com.rincondeltaco.products_service.controler;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rincondeltaco.products_service.models.Producto;
import com.rincondeltaco.products_service.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/producto")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/listar")
    public ResponseEntity<List<Producto>> getProducts() {
        return ResponseEntity.ok(productService.getProducts());
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<Producto> getProduct(@PathVariable int id) {
        Producto producto = productService.getProductById(id);
        if(producto == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(producto);
        }
        return ResponseEntity.ok(producto);
    }

    @GetMapping("/img/{image}")
    public ResponseEntity<Resource> getImage(@PathVariable String image) {
        try {
            Path imagePath = Paths.get("resources/productos/img/").resolve(image).toAbsolutePath();

            File imageFile = imagePath.toFile();

            if(!imageFile.exists() || !imageFile.canRead()) {
                return ResponseEntity.notFound().build();
            }

            Resource resource = new FileSystemResource(imageFile);
            HttpHeaders headers = new HttpHeaders();
            String mimeType = Files.probeContentType(imagePath);
            headers.setContentType(MediaType.parseMediaType(mimeType));

            return ResponseEntity.ok()
                    .headers(headers)
                    .body(resource);

        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.internalServerError().build();
        }
    }

    @PostMapping("/guardar")
    public ResponseEntity<Map<String, Object>> saveProduct(@RequestParam("image") MultipartFile image, @RequestParam("data") String data) throws JsonProcessingException {
        Producto prod = new ObjectMapper().readValue(data, Producto.class);

        boolean _resultado = false;
        boolean imgGuardada = false;

        if (prod != null){
            prod.setRutaImg("/img/"+prod.getRutaImg());
            ResponseEntity.status(HttpStatus.CREATED).body(productService.addProduct(prod));
            _resultado = true;
        }

        if (image != null && !image.isEmpty()) {
            String folderPath = "resources/productos/img";
            File directory = new File(new File("").getAbsolutePath(), folderPath);

            if (!directory.exists()) {
                directory.mkdirs(); // Crea la carpeta si no existe
            }
            String imagePath = directory.getAbsolutePath() + "/" + image.getOriginalFilename();

            try (OutputStream stream = new FileOutputStream(imagePath)) {
                stream.write(image.getBytes());
                imgGuardada = true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        Map<String, Object> response = new HashMap<>();
        if (_resultado == false || imgGuardada == false) {
            response.put("valor", false);
            response.put("msg", "Error al ingresar el producto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        response.put("valor", true);
        response.put("msg", prod.getNomProd() + " fue agregado exitosamente!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping("/editar")
    public ResponseEntity<Map<String, Object>> editProduct(@RequestParam("data") String data) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();

        Producto prod = new ObjectMapper().readValue(data, Producto.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String prodJson = objectMapper.writeValueAsString(prod);
        System.out.println(prodJson);

        if (prod == null) {
            response.put("valor", false);
            response.put("msg", "Error al ingresar el producto");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        productService.editProduct(prod);
        response.put("valor", true);
        response.put("msg", prod.getNomProd() + " fue editado exitosamente!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> deleteProduct(@PathVariable int id){
        Optional<Producto> productOptional = productService.deleteProductById(id);
        if(productOptional.isPresent()){
            return ResponseEntity.ok(productOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }
}

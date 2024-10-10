package com.rincondeltaco.users_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rincondeltaco.users_service.models.Usuario;
import com.rincondeltaco.users_service.service.UserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/usuario")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private HttpSession session;

    @GetMapping("/listar")
    public List<Usuario> listar(){
        return userService.listarUsuarios();
    }

    @PostMapping("/guardar")
    public ResponseEntity<Map<String, Object>> registrar(@RequestParam("data") String data) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        Usuario usu = new ObjectMapper().readValue(data, Usuario.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuJson = objectMapper.writeValueAsString(usu);
        System.out.println(usuJson);

        if (usu == null) {
            response.put("valor", false);
            response.put("msg", "Error al registrar el usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userService.guardarUsuario(usu);
        response.put("valor", true);
        response.put("msg", "El usuario " + usu.getNomUsu() + " fue registrado exitosamente!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestParam("data") String data) throws JsonProcessingException{
        Map<String, Object> response = new HashMap<>();
        Usuario usu = new ObjectMapper().readValue(data, Usuario.class);

        Usuario user = userService.loginUsuarioObj(usu);
        if(user == null){
            response.put("valor", false);
            response.put("msg", "Credenciales Incorrectas");
            return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
        }
        session.setAttribute("usuario", user);
        response.put("valor", true);
        response.put("msg", "Bienvenido " + user.getNomUsu() + "!");
        response.put("name", user.getNomUsu() + " " + user.getApeUsu());
        response.put("rol", user.getRolUsu());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cerrarsesion")
    public ResponseEntity<Void> cerrarSesion() {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar")
    public ResponseEntity<Map<String, Object>> actualizar(@RequestParam("data") String data) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        Usuario usu = new ObjectMapper().readValue(data, Usuario.class);

        ObjectMapper objectMapper = new ObjectMapper();
        String usuJson = objectMapper.writeValueAsString(usu);
        System.out.println(usuJson);

        if (usu == null) {
            response.put("valor", false);
            response.put("msg", "Error al editar el usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userService.guardarUsuario(usu);
        response.put("valor", true);
        response.put("msg", "El usuario " + usu.getNomUsu() + " fue actualizado exitosamente!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<?> eliminar(@PathVariable int id){

        Optional<Usuario> usuarioOptional = userService.eliminarUsuario(id);
        if(usuarioOptional.isPresent()){
            return ResponseEntity.ok(usuarioOptional.orElseThrow());
        }
        return ResponseEntity.notFound().build();
    }

}

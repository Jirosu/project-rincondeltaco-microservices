package com.rincondeltaco.users_service.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rincondeltaco.users_service.models.LoginUsuario;
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
    public ResponseEntity<Map<String, Object>> registrar(@RequestBody Usuario ususario) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();
        System.out.println(ususario);

        if (ususario == null) {
            response.put("valor", false);
            response.put("msg", "Error al registrar el usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userService.guardarUsuario(ususario);
        response.put("valor", true);
        response.put("msg", "El usuario " + ususario.getNomUsu() + " fue registrado exitosamente!");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginUsuario loginUser) throws JsonProcessingException{
        Map<String, Object> response = new HashMap<>();

        Usuario user = userService.loginUsuarioObj(loginUser);
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
        response.put("id", user.getCodUsu());
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/cerrarsesion")
    public ResponseEntity<Void> cerrarSesion() {
        session.invalidate();
        return ResponseEntity.ok().build();
    }

    @PutMapping("/editar")
    public ResponseEntity<Map<String, Object>> actualizar(@RequestBody Usuario ususario) throws JsonProcessingException {
        Map<String, Object> response = new HashMap<>();

        System.out.println(ususario);

        if (ususario == null) {
            response.put("valor", false);
            response.put("msg", "Error al editar el usuario");
            return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
        }

        userService.guardarUsuario(ususario);
        response.put("valor", true);
        response.put("msg", "El usuario " + ususario.getNomUsu() + " fue actualizado exitosamente!");
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

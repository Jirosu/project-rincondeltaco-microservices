package com.rincondeltaco.users_service.service;

import com.rincondeltaco.users_service.models.Usuario;
import com.rincondeltaco.users_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Usuario> listarUsuarios() {
        return userRepository.findAll();
    }

    public Usuario guardarUsuario(Usuario u) {
        u.setContrasena(passwordEncoder.encode(u.getContrasena()));
        return userRepository.save(u);
    }

    public Optional<Usuario> obtenerUsuario(int id) {
        return userRepository.findById(id);
    }


    public boolean loginUsuario(Usuario u, String rol) {
        List<Usuario> lstUsuarios= userRepository.findAll();
        List<Usuario> lstPorRol = lstUsuarios.stream().filter(usu -> usu.getRolUsu().equals(rol)).toList();

        Usuario usuEnc = lstPorRol.stream().filter(usu -> u.getEmail().equals(usu.getEmail()) && passwordEncoder.matches(u.getContrasena(), usu.getContrasena())).findFirst().orElse(null);

        return usuEnc == null ? false : true;
    }


    public Usuario loginUsuarioObj(Usuario u) {
        Usuario userEncontrado = userRepository.findByEmail(u.getEmail());
        if(userEncontrado == null) {
            System.out.println("usuario no encontrado + user: "+ userEncontrado);
            return null;
        }
        if(!passwordEncoder.matches(u.getContrasena(), userEncontrado.getContrasena())){
            System.out.println("password incorrecta + user: "+ userEncontrado);
            return null;
        }
        System.out.println("password correcta + user: "+ userEncontrado);
        return userEncontrado;
    }

    public Optional<Usuario> eliminarUsuario(int id) {

        Optional<Usuario> usuarioOptional = userRepository.findById(id);
        usuarioOptional.ifPresent(usuario -> {
            userRepository.delete(usuario);
        });
        return usuarioOptional;
    }
}

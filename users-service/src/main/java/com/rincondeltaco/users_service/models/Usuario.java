package com.rincondeltaco.users_service.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user")
    private int codUsu;

    @Column(name="name_user")
    private String nomUsu;

    @Column(name = "last_name_user")
    private String apeUsu;

    @Column(name = "email_user")
    private String email;

    @Column(name = "password_user")
    private String contrasena;

    @Column(name = "rol_user")
    private String rolUsu;
}

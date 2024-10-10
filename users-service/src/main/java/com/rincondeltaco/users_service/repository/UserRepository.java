package com.rincondeltaco.users_service.repository;

import com.rincondeltaco.users_service.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByEmail(String email);
}

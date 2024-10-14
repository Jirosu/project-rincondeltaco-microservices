package com.rincondeltaco.products_service.repository;

import com.rincondeltaco.products_service.models.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Categoria, Integer> {
}

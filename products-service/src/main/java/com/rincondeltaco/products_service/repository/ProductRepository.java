package com.rincondeltaco.products_service.repository;

import com.rincondeltaco.products_service.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Integer> {
}

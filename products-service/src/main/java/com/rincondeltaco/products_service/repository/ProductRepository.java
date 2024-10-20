package com.rincondeltaco.products_service.repository;

import com.rincondeltaco.products_service.models.Producto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Producto, Integer> {

    @Query("SELECT p.precProd FROM Producto p WHERE p.codProd = :id")
    Double findPrecProdByCodProd(Integer id);
}

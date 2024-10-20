package com.rincondeltaco.products_service.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rincondeltaco.products_service.models.Producto;
import com.rincondeltaco.products_service.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Producto> getProducts() {
        return productRepository.findAll();
    }

    public Producto getProductById(int id) {
        Optional<Producto> product = productRepository.findById(id);
        return product.orElse(null);
    }

    public List<Double> getListOfPricesByIds(List<Integer> productsIds) {
        List<Double> precios = new ArrayList<>();
        for(int id : productsIds) {
            precios.add(productRepository.findPrecProdByCodProd(id));
        }
        return precios;
    }

    public Producto addProduct(Producto producto) {
        return productRepository.save(producto);
    }

    public Producto editProduct(Producto producto) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        String prodJson = objectMapper.writeValueAsString(producto);
        System.out.println(prodJson);

        return productRepository.save(producto);
    }

    public Optional<Producto> deleteProductById(int id) {
        Optional<Producto> productOptional = productRepository.findById(id);
        productOptional.ifPresent(producto -> {
            productRepository.delete(producto);
        });
        return productOptional;
    }

}

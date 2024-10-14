package com.rincondeltaco.products_service.service;

import com.rincondeltaco.products_service.models.Categoria;
import com.rincondeltaco.products_service.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Categoria> getCategories() {
        return categoryRepository.findAll();
    }
}

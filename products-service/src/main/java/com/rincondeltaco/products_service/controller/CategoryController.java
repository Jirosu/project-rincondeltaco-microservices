package com.rincondeltaco.products_service.controller;

import com.rincondeltaco.products_service.models.Categoria;
import com.rincondeltaco.products_service.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/categoria")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/listar")
    public List<Categoria> getCategories() {
        return categoryService.getCategories();
    }

}

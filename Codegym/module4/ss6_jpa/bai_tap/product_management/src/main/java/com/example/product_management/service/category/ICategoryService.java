package com.example.product_management.service.category;

import com.example.product_management.model.Category;

import java.util.List;

public interface ICategoryService {
    List<Category> findAll();
    Category findById(Long id);
    void save(Category category);
    void deleteById(Long id);
}

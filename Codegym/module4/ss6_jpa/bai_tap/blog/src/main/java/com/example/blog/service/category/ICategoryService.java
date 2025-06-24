package com.example.blog.service.category;

import com.example.blog.model.Category;

import java.util.List;
import java.util.Optional;

public interface ICategoryService {
    List<Category> getAllCategories();
    Optional<Category> getCategoryById(Long id);
    Category saveCategory(Category category);
    void deleteCategory(Long id);
}

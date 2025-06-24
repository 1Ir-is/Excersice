package com.example.blog.controller;

import com.example.blog.model.Category;

import com.example.blog.service.category.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private ICategoryService categoryService;

    @GetMapping
    public String listCategories(Model model) {
        List<Category> categories = categoryService.getAllCategories();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/add")
    public String showAddCategoryForm(Model model) {
        model.addAttribute("category", new Category());
        return "category/create";
    }

    @PostMapping("/add")
    public String addCategory(@ModelAttribute Category category) {
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/edit/{id}")
    public String showEditCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.getCategoryById(id).orElseThrow(() -> new IllegalArgumentException("Invalid category ID: " + id));
        model.addAttribute("category", category);
        return "category/edit";
    }

    @PostMapping("/edit/{id}")
    public String editCategory(@PathVariable Long id, @ModelAttribute Category category) {
        category.setId(id);
        categoryService.saveCategory(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteCategory(id);
        return "redirect:/categories";
    }
}
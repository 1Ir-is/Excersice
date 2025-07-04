package com.example.blog.controllers;

import com.example.blog.models.Category;
import com.example.blog.services.category.ICategoryService;
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

    @GetMapping()
    public String showCategoryList(Model model) {
        List<Category> categories = categoryService.findAll();
        model.addAttribute("categories", categories);
        return "category/list";
    }

    @GetMapping("/create")
    public String showCreateCategoryForm(Model model) {
        Category category = new Category();
        model.addAttribute("category", category);
        return "category/create";
    }

    @PostMapping("/create")
    public String createCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/update/{id}")
    public String showUpdateCategoryForm(@PathVariable Long id, Model model) {
        Category category = categoryService.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid category id: " + id)
        );
        model.addAttribute("category", category);
        return "category/update";
    }

    @PostMapping("/update")
    public String updateCategory(@ModelAttribute Category category) {
        categoryService.save(category);
        return "redirect:/categories";
    }

    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return "redirect:/categories";
    }
}

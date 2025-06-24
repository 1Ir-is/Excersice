package com.example.product_management.controller;

import com.example.product_management.model.Category;
import com.example.product_management.model.Product;
import com.example.product_management.service.category.ICategoryService;
import com.example.product_management.service.product.IProductService;
import com.example.product_management.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    IProductService productService;

    @Autowired
    ICategoryService categoryService;

    @GetMapping()
    public String showProductList(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "product/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        Product p = new Product();
        p.setCategory(new Category());
        model.addAttribute("product", p);
        model.addAttribute("categories", categoryService.findAll());
        return "product/create";
    }



    @PostMapping("/create")
    public String createForm(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String viewProduct(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        model.addAttribute("product", product);
        return "product/detail";
    }

    @GetMapping("/update/{id}")
    public String showUpdateForm(@PathVariable Long id, Model model) {
        Product product = productService.findById(id);
        List<Category> categories = categoryService.findAll();
        model.addAttribute("product", product);
        model.addAttribute("categories", categories);
        return "product/update";
    }

    @PostMapping("/update")
    public String updateForm(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id) {
        productService.deleteById(id);
        return "redirect:/products";
    }
}

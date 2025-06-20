package com.example.luyen_tap.controller;

import com.example.luyen_tap.model.Product;
import com.example.luyen_tap.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/products")
public class ProductController {
    @Autowired
    private IProductService productService;

    @GetMapping
    public String getAllProducts(Model model) {
        List<Product> products = productService.getAllProduct();
        model.addAttribute("products", products);
        return "product-list";
    }

    @GetMapping("/{id}")
    public String getProductById(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-detail";
    }

    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("product", new Product());
        return "product-form";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Product product = productService.getProductById(id);
        model.addAttribute("product", product);
        return "product-edit";
    }

    @PostMapping
    public String addProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @PostMapping("/{id}")
    public String updateProduct(@PathVariable int id, @ModelAttribute Product product) {
        product.setId(id);
        productService.update(product);
        return "redirect:/products";
    }

    @PostMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }
}

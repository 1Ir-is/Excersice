package com.example.productmanagement.controller;

import com.example.productmanagement.model.Product;
import com.example.productmanagement.service.IProductService;
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
    public String showListProduct(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/list";
    }

    @GetMapping("/create")
    public String showCreateForm(Model model) {
        model.addAttribute("product", new Product());
        return "/create";
    }

    @PostMapping("/create")
    public String createProduct(@ModelAttribute Product product) {
        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/edit";
    }

    @PostMapping("/edit")
    public String editProduct(@ModelAttribute Product product) {
        productService.update(product.getId(), product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable int id) {
        productService.delete(id);
        return "redirect:/products";
    }

    @GetMapping("/view/{id}")
    public String viewProduct(@PathVariable int id, Model model) {
        model.addAttribute("product", productService.findById(id));
        return "/view";
    }

    @GetMapping("/search")
    public String searchProduct(@RequestParam String name, Model model) {
        model.addAttribute("products", productService.searchByName(name));
        return "/list";
    }
}

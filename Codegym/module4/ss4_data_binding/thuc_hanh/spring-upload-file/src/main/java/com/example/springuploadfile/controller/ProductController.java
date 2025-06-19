package com.example.springuploadfile.controller;

import com.example.springuploadfile.model.Product;
import com.example.springuploadfile.model.ProductForm;
import com.example.springuploadfile.service.IProductService;
import com.example.springuploadfile.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private ServletContext servletContext;

    @Value("${file-upload}")
    private String fileUpload;

    private final IProductService productService = new ProductService();

    @GetMapping("")
    public String index(Model model) {
        List<Product> products = productService.findAll();
        model.addAttribute("products", products);
        return "/index";
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", new ProductForm());
        return modelAndView;
    }

    @PostMapping("/save")
    public ModelAndView saveProduct(@ModelAttribute ProductForm productForm) {
        MultipartFile multipartFile = productForm.getImage();
        String fileName = multipartFile.getOriginalFilename();

        try {
            File dir = new File(fileUpload);
            if (!dir.exists()) {
                dir.mkdirs();
            }

            FileCopyUtils.copy(productForm.getImage().getBytes(), new File(fileUpload + fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }

        Product product = new Product(productForm.getId(), productForm.getName(),
                productForm.getDescription(), fileName);
        productService.save(product);

        ModelAndView modelAndView = new ModelAndView("/create");
        modelAndView.addObject("productForm", productForm);
        modelAndView.addObject("message", "Created new product successfully !");
        return modelAndView;
    }

}
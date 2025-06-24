package com.example.product_management.service.product;

import com.example.product_management.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    Product findById(Long id);
    void save(Product product);
    void deleteById(Long id);
}

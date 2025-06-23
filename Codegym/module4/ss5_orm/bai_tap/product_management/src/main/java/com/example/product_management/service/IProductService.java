package com.example.product_management.service;

import com.example.product_management.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> findAll();
    List<Product> searchByName(String name);
    List<Product> findPage(int page, int size);
    Product findById(int id);
    void save(Product product);
    void delete(int id);
    long count();
}

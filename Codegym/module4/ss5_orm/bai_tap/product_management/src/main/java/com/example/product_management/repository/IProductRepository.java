package com.example.product_management.repository;

import com.example.product_management.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    List<Product> searchByName(String name);
    List<Product> findPage(int offset, int limit);
    Product findById(int id);
    void save(Product product);
    void delete(int id);
    long count();
}

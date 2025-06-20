package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    List<Product> searchByName(String name);
    Product findById(int id);
    void save(Product product);
    void update(int id, Product product);
    void delete(int id);
}

package com.example.luyen_tap.repository;

import com.example.luyen_tap.model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll();
    Product findById(int id);
    void save(Product product);
    void update(Product product);
    void delete(int id);
}

package com.example.luyen_tap.service;

import com.example.luyen_tap.model.Product;

import java.util.List;

public interface IProductService {
    List<Product> getAllProduct();
    Product getProductById(int id);
    void save(Product product);
    void update(Product product);
    void delete(int id);
}

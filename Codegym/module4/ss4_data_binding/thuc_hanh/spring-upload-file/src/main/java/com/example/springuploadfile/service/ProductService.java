package com.example.springuploadfile.service;

import com.example.springuploadfile.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService implements IProductService {
    private final List<Product> products;

    public ProductService() {
        products = new ArrayList<>();
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public Product findById(int id) {
        return products.get(id);
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void update(int id, Product product) {
        int index = products.indexOf(findById(id));
    }

    @Override
    public void remove(int id) {
        products.remove(findById(id));
    }
}

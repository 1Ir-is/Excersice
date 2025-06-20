package com.example.productmanagement.repository;

import com.example.productmanagement.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    private static final List<Product> products = new ArrayList<>();

    static {
        products.add(new Product(1, "Laptop", 1200.0, "High-end laptop", "Dell"));
        products.add(new Product(2, "Smartphone", 800.0, "Latest model", "Samsung"));
    }

    @Override
    public List<Product> findAll() {
        return products;
    }

    @Override
    public List<Product> searchByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : products) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public Product findById(int id) {
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void save(Product product) {
        products.add(product);
    }

    @Override
    public void update(int id, Product product) {
        for (Product existingProduct : products) {
            if (existingProduct.getId() == id) {
                existingProduct.setName(product.getName());
                existingProduct.setDescription(product.getDescription());
                existingProduct.setPrice(product.getPrice());
                existingProduct.setManufacturer(product.getManufacturer());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getId() == id) {
                products.remove(i);
                break;
            }
        }
    }
}

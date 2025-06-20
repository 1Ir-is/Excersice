package com.example.luyen_tap.repository;

import com.example.luyen_tap.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository implements IProductRepository {

    private static final List<Product> products = new ArrayList<>();
    private static int currentId = 1;

    static {
        // Dữ liệu mẫu khởi tạo ban đầu
        products.add(new Product(currentId++, "iPhone 14", 999.99));
        products.add(new Product(currentId++, "Samsung Galaxy S22", 899.99));
        products.add(new Product(currentId++, "Xiaomi Redmi Note 12", 299.99));
    }

    @Override
    public List<Product> findAll() {
        return products;
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
        product.setId(currentId++);
        products.add(product);
    }

    @Override
    public void update(Product product) {
        for (Product existingProduct : products) {
            if (existingProduct.getId() == product.getId()) {
                existingProduct.setName(product.getName());
                existingProduct.setPrice(product.getPrice());
                break;
            }
        }
    }

    @Override
    public void delete(int id) {
        products.removeIf(p -> p.getId() == id);
    }
}


package ss17_IO_Binary_File.bai_tap.bai_tap_1.repository;



import ss17_IO_Binary_File.bai_tap.bai_tap_1.model.Product;

import java.util.List;

public interface IProductRepository {
    void add(Product product);
    List<Product> findAll();
    Product findById(String id);

    List<Product> findByName(String name);
}

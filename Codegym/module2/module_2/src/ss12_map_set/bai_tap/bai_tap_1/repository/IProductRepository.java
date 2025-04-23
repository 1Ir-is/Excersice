package ss12_map_set.bai_tap.bai_tap_1.repository;

import ss12_map_set.bai_tap.bai_tap_1.model.Product;

import java.util.List;

public interface IProductRepository {
    void add(Product product);

    void update(int id, Product newProduct);

    void remove(int id);

    List<Product> findAll();

    Product findById(int id);

    List<Product> findByName(String name);

    void sortByPriceAsc();

    void sortByPriceDesc();
}

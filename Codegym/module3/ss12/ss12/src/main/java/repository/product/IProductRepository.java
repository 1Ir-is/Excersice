package repository.product;

import model.Product;

import java.util.List;

public interface IProductRepository {
    List<Product> findAll(int page, int pageSize);
    List<Product> findByNameAndCategory(String name, int categoryId, int page, int pageSize);
    int countByNameAndCategory(String name, int categoryId);
    Product findById(int id);
    void save(Product product);
    void update(Product product);
    void delete(int id);
}

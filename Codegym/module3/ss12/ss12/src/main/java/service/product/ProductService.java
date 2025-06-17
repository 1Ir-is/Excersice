package service.product;

import model.Product;
import repository.product.IProductRepository;
import repository.product.ProductRepository;

import java.util.List;

public class ProductService implements IProductService {

    private final IProductRepository productRepository = new ProductRepository();

    @Override
    public List<Product> findAll(int page, int pageSize) {
        return productRepository.findAll(page, pageSize);
    }

    @Override
    public List<Product> findByNameAndCategory(String name, int categoryId, int page, int pageSize) {
        return productRepository.findByNameAndCategory(name, categoryId, page, pageSize);
    }

    @Override
    public int countByNameAndCategory(String name, int categoryId) {
        return productRepository.countByNameAndCategory(name, categoryId);
    }

    @Override
    public Product findById(int id) {
        return productRepository.findById(id);
    }

    @Override
    public void save(Product product) {
        productRepository.save(product);
    }

    @Override
    public void update(Product product) {
        productRepository.update(product);
    }

    @Override
    public void delete(int id) {
        productRepository.delete(id);
    }
}

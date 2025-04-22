package ss12_map_set.bai_tap.bai_tap_1.repository;

import ss12_map_set.bai_tap.bai_tap_1.model.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private final List<Product> productList = new ArrayList<>();

    {
        productList.add(new Product(1, "Laptop Dell", 1500));
        productList.add(new Product(2, "iPhone 14", 999));
        productList.add(new Product(3, "Samsung Galaxy S23", 899));
        productList.add(new Product(4, "MacBook Air", 1200));
        productList.add(new Product(5, "Tai nghe Sony", 150));
        productList.add(new Product(6, "Bàn phím cơ", 100));
        productList.add(new Product(7, "Chuột không dây", 50));
        productList.add(new Product(8, "Màn hình LG 24\"", 250));
        productList.add(new Product(9, "Ổ cứng SSD 1TB", 130));
        productList.add(new Product(10, "Loa Bluetooth JBL", 85));
    }

    @Override
    public void add(Product product) {
        productList.add(product);
    }

    @Override
    public void update(int id, Product newProduct) {
        Product product = findById(id);
        if (product != null) {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
        }
    }

    @Override
    public void remove(int id) {
        productList.remove(findById(id));
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(int id) {
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getId() == id) {
                return productList.get(i);
            }
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (int i = 0; i < productList.size(); i++) {
            if (productList.get(i).getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(productList.get(i));
            }
        }
        return result;
    }

    @Override
    public void sortByPriceAsc() {
        productList.sort((p1, p2) -> (int) (p1.getPrice() - p2.getPrice()));
    }

    @Override
    public void sortByPriceDesc() {
        productList.sort((a, b) -> Double.compare(b.getPrice(), a.getPrice()));
    }
}

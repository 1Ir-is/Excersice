package ss12_map_set.bai_tap.bai_tap_1.repository;

import ss12_map_set.bai_tap.bai_tap_1.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final String FILE_PATH = "ss12_map_set/bai_tap/bai_tap_1/data/products.csv";

    private final List<Product> productList = new ArrayList<>();

    public ProductRepository() {
        loadFromCSV();
    }

    @Override
    public void add(Product product) {
        productList.add(product);
        saveToCSV();
    }

    @Override
    public void update(int id, Product newProduct) {
        Product product = findById(id);
        if (product != null) {
            product.setName(newProduct.getName());
            product.setPrice(newProduct.getPrice());
            saveToCSV();
        }
    }

    @Override
    public void remove(int id) {
        Product product = findById(id);
        if (product != null) {
            productList.remove(product);
            saveToCSV();
        }
    }

    @Override
    public List<Product> findAll() {
        return productList;
    }

    @Override
    public Product findById(int id) {
        for (Product product : productList) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> result = new ArrayList<>();
        for (Product product : productList) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                result.add(product);
            }
        }
        return result;
    }

    @Override
    public void sortByPriceAsc() {
        productList.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        saveToCSV();
    }

    @Override
    public void sortByPriceDesc() {
        productList.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        saveToCSV();
    }

    private void loadFromCSV() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String name = parts[1];
                double price = Double.parseDouble(parts[2]);
                productList.add(new Product(id, name, price));
            }
        } catch (IOException e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.out.println("Error number: " + e.getMessage());
        }
    }

    private void saveToCSV() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH))) {
            for (Product product : productList) {
                writer.write(product.getId() + "," + product.getName() + "," + product.getPrice());
                writer.newLine();

            }
        } catch (IOException e) {
            System.out.println("Error writing to CSV file: " + e.getMessage());

        }
    }
}
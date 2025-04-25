package ss17_IO_Binary_File.bai_tap.bai_tap_1.repository;

import ss17_IO_Binary_File.bai_tap.bai_tap_1.model.Product;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository implements IProductRepository {
    private static final String FILE_PATH = "ss17_IO_Binary_File/bai_tap/bai_tap_1/data/products.dat";

    private void writeToFile(List<Product> products) {
        try {
            File file = new File(FILE_PATH);
            File parent = file.getParentFile();
            if (!parent.exists()) {
                parent.mkdirs();
            }

            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
                oos.writeObject(products);
            }
        } catch (IOException e) {
            System.out.println("Error writing file: " + e.getMessage());
        }
    }

    private List<Product> readFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) return new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Product>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error reading file: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    @Override
    public void add(Product product) {
        List<Product> products = readFromFile();
        products.add(product);
        writeToFile(products);
    }

    @Override
    public List<Product> findAll() {
        return readFromFile();
    }

    @Override
    public Product findById(String id) {
        List<Product> products = readFromFile();
        for (Product product : products) {
            if (product.getId().equalsIgnoreCase(id)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public List<Product> findByName(String name) {
        List<Product> matchedProducts = new ArrayList<>();
        for (Product product : readFromFile()) {
            if (product.getName().toLowerCase().contains(name.toLowerCase())) {
                matchedProducts.add(product);
            }
        }
        return matchedProducts;
    }
}

package ss17_IO_Binary_File.bai_tap.bai_tap_1.service;

import ss17_IO_Binary_File.bai_tap.bai_tap_1.model.Product;
import ss17_IO_Binary_File.bai_tap.bai_tap_1.repository.IProductRepository;
import ss17_IO_Binary_File.bai_tap.bai_tap_1.repository.ProductRepository;

import java.util.List;
import java.util.PrimitiveIterator;
import java.util.Scanner;

public class ProductService implements IProductService{
    private final IProductRepository productRepository = new ProductRepository();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addProduct() {
        System.out.println("\n--- Add Product ---");

        System.out.print("Enter product ID: ");
        String id = scanner.nextLine();

        System.out.print("Enter product name: ");
        String name = scanner.nextLine();

        double price = 0;
        while (true) {
            try {
                System.out.print("Enter price: ");
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price <= 0) {
                    System.out.println("Price must greater than 0!");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Invalid");
            }
        }

        System.out.print("Enter manufacturer: ");
        String manufacture = scanner.nextLine();

        System.out.print("Enter description: ");
        String description = scanner.nextLine();

        Product product = new Product(id, name, price, manufacture, description);
        productRepository.add(product);
        System.out.println("Product added successfully!");
    }

    @Override
    public void displayProduct() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            System.out.println("No product found!");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Override
    public void searchByName() {
        System.out.print("Enter name of product: ");
        String name = scanner.nextLine();
        List<Product> products = productRepository.findByName(name);
        if (products.isEmpty()) {
            System.out.println("Cannot find!");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }
}

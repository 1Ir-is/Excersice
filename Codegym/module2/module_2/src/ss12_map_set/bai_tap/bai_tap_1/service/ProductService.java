package ss12_map_set.bai_tap.bai_tap_1.service;

import ss12_map_set.bai_tap.bai_tap_1.model.Product;
import ss12_map_set.bai_tap.bai_tap_1.repository.IProductRepository;
import ss12_map_set.bai_tap.bai_tap_1.repository.ProductRepository;

import java.util.List;
import java.util.Scanner;

public class ProductService implements IProductService {
    private final IProductRepository productRepository = new ProductRepository();
    private final Scanner scanner = new Scanner(System.in);

    @Override
    public void addProduct() {
        System.out.print("Nhập id: ");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá sản phẩm: ");
        double price = Double.parseDouble(scanner.nextLine());

        Product product = new Product(id, name, price);
        productRepository.add(product);
        System.out.println("Sản phẩm thêm thành công!");
    }

    @Override
    public void editProduct() {
        System.out.print("Nhập id để cập nhập: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product existingProduct = productRepository.findById(id);

        if (existingProduct != null) {
            System.out.print("Nhập tên mới: ");
            String name = scanner.nextLine();
            System.out.print("Nhập giá mới: ");
            double price = Double.parseDouble(scanner.nextLine());

            Product updatedProduct = new Product(id, name, price);
            productRepository.update(id, updatedProduct);
            System.out.println("Cập nhập thành công!");
        } else {
            System.out.println("Sản phẩm không tìm thấy!");
        }
    }

    @Override
    public void removeProduct() {
        System.out.print("Nhập id để xoá: ");
        int id = Integer.parseInt(scanner.nextLine());
        Product product = productRepository.findById(id);

        if (product != null) {
            productRepository.remove(id);
            System.out.println("Xoá sản phẩm thành công!");
        } else {
            System.out.println("Sản phẩm không tìm thấy!");
        }
    }

    @Override
    public void displayProducts() {
        List<Product> products = productRepository.findAll();
        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào!");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Override
    public void searchByName() {
        System.out.print("Nhập tên sản phẩm: ");
        String name = scanner.nextLine();
        List<Product> products = productRepository.findByName(name);

        if (products.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm nào!");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    @Override
    public void sortByPriceAsc() {
        productRepository.sortByPriceAsc();
        System.out.println("Sắp xếp theo giá tăng dần!");
    }

    @Override
    public void sortByPriceDesc() {
        productRepository.sortByPriceDesc();
        System.out.println("Sắp xếp theo giá giảm dần!");
    }
}
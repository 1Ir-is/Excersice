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

        System.out.print("Nhập tên: ");
        String name = scanner.nextLine();

        System.out.print("Nhập giá: ");
        double price = Double.parseDouble(scanner.nextLine());

        productRepository.add(new Product(id, name, price));
        System.out.println("Thêm sản phẩm thành công!");
    }

    @Override
    public void editProduct() {
        System.out.print("Nhập id cần sửa: ");
        int id = Integer.parseInt(scanner.nextLine());

        Product productExisting = productRepository.findById(id);
        if (productExisting == null) {
            System.out.println("Không tìm thấy sản phẩm này!");
            return;
        }
        System.out.print("Nhập tên mới: ");
        String name = scanner.nextLine();
        System.out.print("Nhập giá mới: ");
        double price = Double.parseDouble(scanner.nextLine());
        productRepository.update(id, new Product(id, name, price));
        System.out.println("Cập nhập sản phẩm thành công! ");
    }

    @Override
    public void removeProduct() {
        System.out.print("Nhập id cần xoá: ");
        int id = Integer.parseInt(scanner.nextLine());
        productRepository.remove(id);
        System.out.println("Xoá sản phẩm có id " + id + " thành công!");
    }

    @Override
    public void displayProducts() {
        List<Product> productList = productRepository.findAll();
        for (int i = 0; i < productList.size(); i++) {
            System.out.println(productList.get(i));
        }
    }

    @Override
    public void searchByName() {
        System.out.print("Nhập tên bạn cần tìm: ");
        String name = scanner.nextLine();

        List<Product> productList = productRepository.findByName(name);
        if (productList.isEmpty()) {
            System.out.println("Không tìm thấy sản phẩm này!");
        } else {
            for (int i = 0; i < productList.size(); i++) {
                System.out.println(productList.get(i));
            }
        }
    }

    @Override
    public void sortByPriceAsc() {
        productRepository.sortByPriceAsc();
        System.out.println("Đã sắp xếp giá theo tăng dần!");
        displayProducts();
    }

    @Override
    public void sortByPriceDesc() {
        productRepository.sortByPriceDesc();
        System.out.println("Đã sắp xếp giá theo giảm dần");
        displayProducts();
    }
}

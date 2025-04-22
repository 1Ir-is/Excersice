package ss12_map_set.bai_tap.bai_tap_1.controller;

import ss12_map_set.bai_tap.bai_tap_1.service.IProductService;
import ss12_map_set.bai_tap.bai_tap_1.service.ProductService;

import java.util.Scanner;


public class ProductController {
    private final IProductService productService = new ProductService();
    private Scanner scanner = new Scanner(System.in);

    public void Run() {
        int choice;
        do {
            System.out.println("\n========= MENU =========");
            System.out.println("1. Thêm sản phẩm");
            System.out.println("2. Sửa sản phẩm");
            System.out.println("3. Xóa sản phẩm");
            System.out.println("4. Hiển thị sản phẩm");
            System.out.println("5. Tìm kiếm sản phẩm");
            System.out.println("6. Sắp xếp tăng dần theo giá");
            System.out.println("7. Sắp xếp giảm dần theo giá");
            System.out.println("0. Thoát");
            System.out.print("Chọn: ");
            choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    productService.addProduct();
                    break;
                case 2:
                    productService.editProduct();
                    break;
                case 3:
                    productService.removeProduct();
                    break;
                case 4:
                    productService.displayProducts();
                    break;
                case 5:
                    productService.searchByName();
                    break;
                case 6:
                    productService.sortByPriceAsc();
                    break;
                case 7:
                    productService.sortByPriceDesc();
                    break;
                case 0:
                    if (confirmExit()) {
                        System.out.println("Thoát chương trình!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! ");
            }
        } while (choice != 0);
    }

    private boolean confirmExit() {
        while (true) {
            System.out.print("Bạn có chắc chắn muốn thoát khỏi chương trình không (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                System.out.println("Không hợp lệ!");
            }
        }
    }
}

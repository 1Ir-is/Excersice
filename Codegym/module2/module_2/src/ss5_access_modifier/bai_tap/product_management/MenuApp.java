package ss5_access_modifier.bai_tap.product_management;

import ss3_mang_va_phuong_thuc.bai_tap.menu.MenuPrinter;

import java.util.Scanner;

public class MenuApp {
    private ProductManagement productManagement;

    public MenuApp() {
        productManagement = new ProductManagement();
    }

    public void showMenu(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printMainMenu();
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    productManagement.displayProduct();
                    goBack(scanner);
                    break;
                case 2:
                    productManagement.addProduct(scanner);
                    goBack(scanner);
                    break;
                case 3:
                    productManagement.updateProduct(scanner);
                    goBack(scanner);
                    break;
                case 4:
                    productManagement.deleteProduct(scanner);
                    goBack(scanner);
                    break;
                case 5:
                    productManagement.searchProduct(scanner);
                    goBack(scanner);
                    break;
                case 6:
                    if (confirmExit(scanner)) {
                        System.out.println("Thoát chương trình!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Có lỗi, vui lòng thử lại!");
            }
        } while (choice != 6);
    }

    private boolean confirmExit(Scanner scanner) {
        System.out.print("Bạn có muốn thoát chương trình không (Y/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();
        return confirmation.equals("Y");
    }

    private void goBack(Scanner scanner) {
        System.out.println("Bấm Enter để quay về...");
        scanner.nextLine();
    }
}

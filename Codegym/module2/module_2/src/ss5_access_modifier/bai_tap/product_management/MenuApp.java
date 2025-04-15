package ss5_access_modifier.bai_tap.product_management;


import java.util.InputMismatchException;
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
            choice = validateMenuChoice(scanner);

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
                    productManagement.sortByName();
                    goBack(scanner);
                    break;
                case 7:
                    productManagement.sortByPrice();
                    goBack(scanner);
                    break;
                case 8:
                    if (confirmExit(scanner)) {
                        System.out.println("Thoát chương trình!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ, vui lòng thử lại!");
            }
        } while (choice != 8);
    }

    private int validateMenuChoice(Scanner scanner) {
        int choice = -1;
        while (true) {
            try {
                choice = scanner.nextInt();
                scanner.nextLine();
                if (choice >= 1 && choice <= 8) {
                    return choice;
                } else {
                    System.out.print("Lựa chọn phải từ 1 đến 8. Vui lòng nhập lại: ");
                }
            } catch (InputMismatchException e) {
                System.out.print("Lựa chọn không hợp lệ. Vui lòng nhập số: ");
                scanner.nextLine();
            }
        }
    }

    private boolean confirmExit(Scanner scanner) {
        while (true) {
            System.out.print("Bạn có muốn thoát chương trình không (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                System.out.println("Lựa chọn không hợp lệ. Vui lòng nhập Y hoặc N.");
            }
        }
    }

    private void goBack(Scanner scanner) {
        System.out.println("Bấm Enter để quay về...");
        scanner.nextLine();
    }
}
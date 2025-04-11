package ss5_access_modifier.bai_tap.product_management;

import ss3_mang_va_phuong_thuc.bai_tap.menu.MenuPrinter;

import java.util.Scanner;

public class MenuApp {
    public void showMenu(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printMainMenu();
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    break;
                case 2:
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                default:
                    System.out.println("Có lỗi, vui lòng thử lại!");
            }
        } while (choice != 6);
    }

    private
}

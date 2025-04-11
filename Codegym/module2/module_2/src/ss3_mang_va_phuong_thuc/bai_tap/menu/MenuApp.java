package ss3_mang_va_phuong_thuc.bai_tap.menu;

import java.util.Scanner;

public class MenuApp {
    private StudentManagement studentManagement;

    public MenuApp() {
        studentManagement = new StudentManagement();
    }

    public void show(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printMainMenu();
            System.out.print("Nhập lựa chọn: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    studentManagement.displayStudent();
                    goBack(scanner);
                    break;
                case 2:
                    studentManagement.addStudent(scanner);
                    goBack(scanner);
                    break;
                case 3:
                    studentManagement.updateStudent(scanner);
                    goBack(scanner);
                    break;
                case 4:
                    studentManagement.deleteStudent(scanner);
                    goBack(scanner);
                    break;
                case 5:
                    studentManagement.searchStudent(scanner);
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
        System.out.print("Bạn có muốn thoát chương trình (Y/N): ");
        String confirmation = scanner.nextLine().trim().toUpperCase();
        return confirmation.equals("Y");
    }

    private void goBack(Scanner scanner) {
        System.out.println("Bấm Enter để quay về...");
        scanner.nextLine();
    }
}

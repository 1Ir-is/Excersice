package ss3_mang_va_phuong_thuc.bai_tap.menu;

import java.util.Scanner;

public class MenuApp {
    public static void main(String[] args) {
        showMenu();
    }

    public static void showMenu() {
        StudentManagement studentManagement = new StudentManagement();
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println();
            System.out.println("================= [ MENU ] ==================");
            System.out.println("1. Hiển thị");
            System.out.println("2. Thêm sinh viên");
            System.out.println("3. Cập nhập thông tin sinh viên");
            System.out.println("4. Xoá sinh viên");
            System.out.println("5. Tìm sinh viên");
            System.out.println("6. Thoát chương trình");
            System.out.println("=============================================");
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
                    System.out.println("Có lỗi vui lòng thử lại!");
            }
        } while (choice != 6);

        scanner.close();
    }

    public static boolean confirmExit(Scanner scanner) {
        System.out.print("Bạn có muốn thoát chương trình (Y/N): ");
        String confirmation = scanner.next().trim().toUpperCase();
        return confirmation.equals("Y");
    }

    public static void goBack(Scanner scanner) {
        System.out.println("Bấm Enter để quay về...");
        scanner.nextLine();
    }
}
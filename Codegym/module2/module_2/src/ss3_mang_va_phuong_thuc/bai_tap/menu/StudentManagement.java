package ss3_mang_va_phuong_thuc.bai_tap.menu;

import java.util.Scanner;

public class StudentManagement {
    private static final int MAX_STUDENT = 100;
    private Student[] student = new Student[MAX_STUDENT];
    private int studentCout = 0;

    public StudentManagement() {
        String[] names = {"Vương", "Chiến", "Vĩnh"};
        int[] ids = {101, 102, 103};

        for (int i = 0; i < 3; i++) {
            student[studentCout++] = new Student(names[i], ids[i]);
        }
    }

    public void addStudent(Scanner scanner) {
        if (studentCout >= MAX_STUDENT) {
            System.out.println("Không thể thêm học sinh! Đã đầy danh sách.");
            return;
        }

        System.out.print("Nhập ID học sinh: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Nhập tên học sinh: ");
        String name = scanner.nextLine();

        student[studentCout++] = new Student(name, id);
        System.out.println("Thêm học sinh thành công!");
    }

    public void displayStudent() {
        if (studentCout == 0) {
            System.out.println("Hiện tại không có học sinh nào!");
        } else {
            System.out.println("Danh sách sinh viên:");
            for (int i = 0; i < studentCout; i++) {
                System.out.println(student[i]);
            }
        }
    }


}

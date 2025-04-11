package ss3_mang_va_phuong_thuc.bai_tap.menu;

import java.util.Scanner;

public class StudentManagement {
    private static final int MAX_STUDENT = 100;
    private Student[] student = new Student[MAX_STUDENT];

    public StudentManagement() {
        student[0] = new Student("Vương", 1);
        student[1] = new Student("Chiến", 2);
        student[2] = new Student("Vĩnh", 3);
    }


    public void addStudent(Scanner scanner) {
        for (int i = 0; i < student.length; i++) {
            if (student[i] == null) {
                System.out.print("Nhập ID học sinh: ");
                int id = scanner.nextInt();
                scanner.nextLine();
                System.out.print("Nhập tên học sinh: ");
                String name = scanner.nextLine();

                student[i] = new Student(name, id);
                System.out.println("Thêm học sinh thành công!");
                return;
            }
        }

        System.out.println("Không thể thêm học sinh! Danh sách đã đầy.");
    }


    public void displayStudent() {
        boolean hasStudent = false;

        for (Student value : student) {
            if (value != null) {
                System.out.println(value);
                hasStudent = true;
            }
        }
        if (!hasStudent) {
            System.out.println("Hiện tại không có học sinh nào!");
        }
    }

    public void updateStudent(Scanner scanner) {
        while (true) {
            System.out.print("Nhập id sinh viên để cập nhập: ");
            int id = scanner.nextInt();
            scanner.nextLine();
            Student student = findStudentById(id);
            if (student != null) {
                System.out.print("Nhập tên mới cho sinh viên: ");
                String name = scanner.nextLine();
                student.setName(name);
                System.out.println("Cập nhập thông tin cho sinh viên " + id + " thanh cong!");
                break;
            } else {
                System.out.println("Sinh viên không tồn tại!");
            }
        }
    }

    public void deleteStudent(Scanner scanner) {
        System.out.print("Nhập id sinh viên để xoá: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        int index = findStudentIndexById(id);

        if (index != -1) {
            for (int i = 0; i < MAX_STUDENT - 1; i++) {
                student[i] = student[i + 1];
            }
            student[MAX_STUDENT - 1] = null;
            System.out.println("Xoá sinh viên có id " + id + " thanh cong");
        } else {
            System.out.println("Không tìm thấy sinh viên này!");
        }
    }

    private Student findStudentById(int id) {
        for (Student student : student) {
            if (student != null && student.getId() == id) {
                return student;
            }
        }
        return null;
    }

    private int findStudentIndexById(int id) {
        for (int i = 0; i < MAX_STUDENT; i++) {
            if (student[i] != null && student[i].getId() == id) {
                return i;
            }
        }
        return -1;
    }
}

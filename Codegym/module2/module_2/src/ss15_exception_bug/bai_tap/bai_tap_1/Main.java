package ss15_exception_bug.bai_tap.bai_tap_1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.print("Nhap a: ");
            double a = Double.parseDouble(scanner.nextLine());

            System.out.print("Nhap b: ");
            double b = Double.parseDouble(scanner.nextLine());

            System.out.print("Nhap c: ");
            double c = Double.parseDouble(scanner.nextLine());

            Triangle triangle = new Triangle(a, b, c);
            triangle.display();
        } catch (IllegalTriangleException e) {
            System.out.println("Loi: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Dau vao khong hop le");
        } finally {
            scanner.close();
            System.out.println("Thoat chuong trinh");
        }
    }
}

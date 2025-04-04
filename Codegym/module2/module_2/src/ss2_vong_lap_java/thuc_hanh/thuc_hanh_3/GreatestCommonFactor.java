package ss2_vong_lap_java.thuc_hanh.thuc_hanh_3;

import java.util.Scanner;

public class GreatestCommonFactor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so thu 1: ");
        int a = scanner.nextInt();
        System.out.println("Nhap so thu 2: ");
        int b = scanner.nextInt();

        a = Math.abs(a);
        b = Math.abs(b);

        while (a != b) {
            if (a > b) {
                a = a - b;
            } else {
                b = b - a;
            }
        }

        System.out.println("Uoc so chung lon nhat la: " + a);
    }
}

package ss1_tong_quan_java.bai_tap.bai_tap_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MoneyExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rate = 25000;
        System.out.println("Chon chuc nang");
        System.out.println("1. USD -> VND");
        System.out.println("2. VND -> USD");

        System.out.print("Nhap lua chon cua ban: ");
        try {
            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.println("Nhap so tien USD: ");
                try {
                    double usd = scanner.nextDouble();
                    if (usd > 0) {
                        double vnd = usd * rate;
                        System.out.println("Tien doi la: " + vnd + " VND");
                    } else {
                        System.out.println("So tien nhap vao phai lon hon 0!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Vui long nhap mot so hop le!");
                }
            } else if (choice == 2) {
                System.out.println("Nhap so tien VND: ");
                try {
                    double vnd = scanner.nextDouble();
                    if (vnd > 0) {
                        double usd = vnd / rate;
                        System.out.println("Tien doi la: " + usd + " DOLLAR");
                    } else {
                        System.out.println("So tien nhap vao phai lon hon 0!");
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Vui long nhap mot so hop le!");
                }
            } else {
                System.out.println("Lua chon khong hop le!");
            }
        } catch (InputMismatchException e) {
            System.out.println("Vui long nhap mot lua chon hop le!");
        }
    }
}
package ss1_tong_quan_java.bai_tap.bai_tap_2;

import java.util.InputMismatchException;
import java.util.Scanner;

public class MoneyExchange {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rate = 25000;
        int choice = 0;
        boolean validChoice = false;

        do {
            System.out.println("Chon chuc nang");
            System.out.println("1. USD -> VND");
            System.out.println("2. VND -> USD");
            System.out.print("Nhap lua chon cua ban: ");
            try {
                choice = scanner.nextInt();
                if (choice == 1 || choice == 2) {
                    validChoice = true;
                } else {
                    System.out.println("Lua chon khong hop le! Vui long chon lai.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Vui long nhap mot lua chon hop le!");
            }
        } while (!validChoice);

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
        } else {
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
        }
    }
}
package ss2_vong_lap_java.thuc_hanh.thuc_hanh_1;

import java.util.Scanner;

public class CheckPrime {
    public static boolean isPrime(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so: ");
        int number = scanner.nextInt();
        if (isPrime(number)) {
            System.out.println(number + " la so nguyen to!");
        } else {
            System.out.println(number + " khong phai la so nguyen to!");
        }
    }
}

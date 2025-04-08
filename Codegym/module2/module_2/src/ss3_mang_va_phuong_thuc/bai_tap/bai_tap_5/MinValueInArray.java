package ss3_mang_va_phuong_thuc.bai_tap.bai_tap_5;

import java.util.Scanner;

public class MinValueInArray {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so luong phan tu cua mang: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Nhap cac phan tu cua mang: ");
        for (int i = 0; i < size; i++) {
            System.out.print("Phan tu thu " + (i + 1) + ": ");
            array[i] = scanner.nextInt();
        }

        int min = array[0];

        for (int i = 1; i < size; i++) {
            if (array[i] < min) {
                min = array[i];
            }
        }

        System.out.println("Gia tri nho nhat trong mang la: " + min);
    }
}


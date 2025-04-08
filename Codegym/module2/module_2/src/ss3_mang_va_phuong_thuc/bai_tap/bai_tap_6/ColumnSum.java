package ss3_mang_va_phuong_thuc.bai_tap.bai_tap_6;

import java.util.Scanner;

public class ColumnSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Nhap so hang cua mang: ");
        int rows = scanner.nextInt();
        System.out.println("Nhap so cot cua mang: ");
        int cols = scanner.nextInt();

        double[][] array = new double[rows][cols];
        System.out.println("Nhap cac phan tu cua mang: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Phan tu [" + i + "][" + j + "]: ");
                array[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Mang vua nhap la:");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(array[i][j] + " ");
            }
            System.out.println();
        }

        System.out.print("Nhap thu tu cua cot muon tinh tong (bat dau tu 0): ");
        int columnIndex = scanner.nextInt();

        if (columnIndex < 0 || columnIndex >= cols) {
            System.out.println("Cot khong hop le!");
        } else {
            double sum = 0;
            for (int i = 0; i < rows; i++) {
                sum += array[i][columnIndex];
            }
            System.out.println("Tong cac phan tu cua cot " + columnIndex + " la: " + sum);
        }
    }
}

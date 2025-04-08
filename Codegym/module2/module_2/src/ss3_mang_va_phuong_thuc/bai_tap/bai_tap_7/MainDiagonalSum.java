package ss3_mang_va_phuong_thuc.bai_tap.bai_tap_7;

import java.util.Scanner;

public class MainDiagonalSum {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap kich thuoc cua ma tran vuong: ");
        int size = scanner.nextInt();

        double[][] matrix = new double[size][size];
        System.out.println("Nhap cac phan tu cua ma tran: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print("Phan tu [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Ma tran vuong vua nhap la: ");
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        double sum = 0;
        for (int i = 0; i < size; i++) {
            sum += matrix[i][i];
        }

        System.out.println("Tong cac phan tu tren duong cheo chinh la: " + sum);
    }
}

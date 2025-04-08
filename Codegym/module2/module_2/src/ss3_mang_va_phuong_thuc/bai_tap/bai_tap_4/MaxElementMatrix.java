package ss3_mang_va_phuong_thuc.bai_tap.bai_tap_4;

import java.util.Scanner;

public class MaxElementMatrix {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap so hang cua mang: ");
        int rows = scanner.nextInt();
        System.out.print("Nhap so cot cua mang: ");
        int cols = scanner.nextInt();

        double[][] matrix = new double[rows][cols];
        System.out.println("Nhap cac phan tu ma tran: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print("Element [" + i + "][" + j + "]: ");
                matrix[i][j] = scanner.nextDouble();
            }
        }

        System.out.println("Ma tran vua nhap la: ");
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }

        double max = matrix[0][0];
        int maxRow = 0;
        int maxCol = 0;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (matrix[i][j] > max) {
                    max = matrix[i][j];
                    maxRow = i;
                    maxCol = j;
                }
            }
        }

        System.out.println("Phan tu lon nhat la: " + max + " tai vi tri [" + maxRow + "][" + maxCol + "]");
    }
}

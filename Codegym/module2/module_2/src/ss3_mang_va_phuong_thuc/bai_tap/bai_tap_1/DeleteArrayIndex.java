package ss3_mang_va_phuong_thuc.bai_tap.bai_tap_1;

import java.util.Scanner;

public class DeleteArrayIndex {
    public static void main(String[] args) {
        int size;
        int[] array;
        Scanner scanner = new Scanner(System.in);
        do {
            System.out.print("Nhap so luong phan tu cua mang: ");
            size = scanner.nextInt();
            if (size > 20)
                System.out.println("So luong khong duoc qua 20!");
        } while (size > 20);

        array = new int[size];
        int i = 0;
        while (i < array.length) {
            System.out.print("Nhap phan tu thu " + (i + 1) + " : ");
            array[i] = scanner.nextInt();
            i++;
        }
        System.out.print("Mang vua nhap la: ");
        for (int j = 0; j < array.length; j++) {
            System.out.print(array[j] + " ");
        }
        System.out.println();

        System.out.print("Nhap phan tu can xoa: ");
        int deleteArray = scanner.nextInt();

        // tim phan tu can xoa
        int index_del = -1;
        for (i = 0; i < array.length; i++) {
            if (array[i] == deleteArray) {
                index_del = i;
                break;
            }
        }

        while (index_del == -1) {
            System.out.println("Phan tu " + deleteArray + " khong tim thay trong mang.");
            System.out.print("Nhap lai phan tu can xoa: ");
            deleteArray = scanner.nextInt();
            for (i = 0; i < array.length; i++) {
                if (array[i] == deleteArray) {
                    index_del = i;
                    break;
                }
            }
        }

        // xoa phan tu
        for (i = index_del; i < array.length - 1; i++) {
            array[i] = array[i + 1];
        }

        // in mang sau khi xoa
        System.out.println("Mang sau khi xoa phan tu " + deleteArray + ": ");
        for (i = 0; i < array.length - 1; i++) {
            System.out.print(array[i] + " ");
        }
    }
}

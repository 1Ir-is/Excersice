package ss3_mang_va_phuong_thuc.bai_tap.bai_tap_2;

import java.util.Scanner;

public class PushArrayIndex {
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

        System.out.print("Nhap phan tu muon them: ");
        int insertValue = scanner.nextInt();

        System.out.println("Nhap vi tri muon them phan tu: ");
        int index = scanner.nextInt();

        if (index <= -1 || index >= array.length) {
            System.out.println("Khong the chen phan tu vao vi tri nay.");
        } else {
//            for (i = array.length - 1; i > index; i--) {
//                array[i] = array[i - 1];
//            }
//            array[index] = insertValue;
            int[] newArray = new int[array.length + 1];

            // sao chep cac phan tu truoc khi chen
            for (i = 0; i < index; i++) {
                newArray[i] = array[i];
            }

            // chen phan tu
            newArray[index] = insertValue;

            // sao chep cac phan tu sau khi chen
            for (i = index; i < array.length; i++) {
                newArray[i + 1] = array[i];
            }

            System.out.println("Mang sau khi chen phan tu " + insertValue + " vao vi tri " + index + " la: ");
            for (i = 0; i < newArray.length; i++) {
                System.out.print(newArray[i] + " ");
            }
        }
    }
}

package ss3_mang_va_phuong_thuc.bai_tap.bai_tap_8;

import java.util.Scanner;

public class CharacterCount {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Nhap chuoi: ");
        String str = scanner.nextLine();

        System.out.print("Nhap ky tu can dem: ");
        char character = scanner.next().charAt(0);

        int count = 0;

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == character) {
                count++;
            }
        }

        System.out.println("So lan xuat hien cua ky tu: '" + character + "' trong chuoi la: " + count);
    }
}

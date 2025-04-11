package ss3_mang_va_phuong_thuc.bai_tap.menu;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuApp menuApp = new MenuApp();
        menuApp.show(scanner);
        scanner.close();
    }
}
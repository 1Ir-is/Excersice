package ss1_tong_quan_java.bai_tap.bai_tap_1;

import java.util.Scanner;

public class HelloWorld {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập tên của bạn: ");
        String name = sc.nextLine();
        System.out.println("Xin chào " + name + ", chúc bạn một ngày tốt lành!");
    }
}

package ss1_tong_quan_java.bai_tap.bai_tap_2;

import java.util.Scanner;

public class MoneyExchange {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int rate = 23000;
        System.out.println("Nhap tien: ");
        int usd = sc.nextInt();
        int vnd = usd * rate;
        System.out.println("Tien doi la: " + vnd + "VND");
    }
}

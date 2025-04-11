package ss5_access_modifier.bai_tap.product_management;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MenuApp menuApp = new MenuApp();
        menuApp.showMenu(scanner);
        scanner.close();
    }
}

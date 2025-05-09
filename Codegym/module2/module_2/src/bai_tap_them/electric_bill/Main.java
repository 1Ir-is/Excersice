package bai_tap_them.electric_bill;

import bai_tap_them.electric_bill.controllers.MainController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        MainController controller = new MainController();
        controller.displayMainMenu();
        scanner.close();
    }
}

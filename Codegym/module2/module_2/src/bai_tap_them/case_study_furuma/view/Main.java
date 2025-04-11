package bai_tap_them.case_study_furuma.view;

import bai_tap_them.case_study_furuma.controllers.FuramaController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        FuramaController controller = new FuramaController();
        controller.displayMainMenu();
        scanner.close();
    }
}

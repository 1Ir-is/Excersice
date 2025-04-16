package bai_tap_them.phuong_tien_giao_thong.views;

import bai_tap_them.phuong_tien_giao_thong.controllers.VehicleController;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        VehicleController vehicleController = new VehicleController();
        vehicleController.displayMenu();
        scanner.close();
    }
}

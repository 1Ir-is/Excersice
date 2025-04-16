package bai_tap_them.phuong_tien_giao_thong.controllers;

import bai_tap_them.phuong_tien_giao_thong.models.Car;
import bai_tap_them.phuong_tien_giao_thong.models.Motorcycle;
import bai_tap_them.phuong_tien_giao_thong.models.Truck;
import bai_tap_them.phuong_tien_giao_thong.models.Vehicle;
import bai_tap_them.phuong_tien_giao_thong.services.IVehicleService;
import bai_tap_them.phuong_tien_giao_thong.services.VehicleService;
import bai_tap_them.phuong_tien_giao_thong.utils.MenuPrinter;

import java.awt.*;
import java.util.Scanner;

public class VehicleController {
    private final IVehicleService service = new VehicleService();
    private final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        int choice;

        do {
            MenuPrinter.printMainMenu();
            choice = validateMenuChoice(scanner, 4);
            switch (choice) {
                case 1:
                    addVehicle();
                    break;
                case 2:
                    displayVehicle();
                    break;
                case 3:
                    deleteVehicle();
                    break;
                case 4:
                    if (confirmExit(scanner)) {
                        System.out.println("Thoát chương trình!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Lựa chọn không hợp lệ! ");

            }
        } while (choice != 4);
    }

    private void addVehicle() {
        MenuPrinter.printAddNewMenu();
        int vehicleType = validateMenuChoice(scanner, 4);
        System.out.print("Nhập biển kiểm soát: ");
        String licensePlate = scanner.nextLine().trim();
        System.out.print("Nhập hãng sản xuất: ");
        String manufacture = scanner.nextLine().trim();
        System.out.print("Nhập năm sản xuất: ");
        int yearOfManufacture = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhập tên chủ sở hữu: ");
        String owner = scanner.nextLine().trim();

        switch (vehicleType) {
            case 1:
                System.out.print("Nhập tải trọng (tấn): ");
                double loadCapacity = Double.parseDouble(scanner.nextLine().trim());
                service.addVehicle(new Truck(licensePlate, manufacture, yearOfManufacture, owner, loadCapacity));
                System.out.println("Thêm xe tải mới thành công!");
                break;
            case 2:
                System.out.print("Nhập số chỗ ngồi: ");
                int numberOfSeats = Integer.parseInt(scanner.nextLine().trim());
                System.out.print("Nhập kiểu xe (du lịch, xe khách): ");
                String carType = scanner.nextLine().trim();
                service.addVehicle(new Car(licensePlate, manufacture, yearOfManufacture, owner, numberOfSeats, carType));
                System.out.println("Thêm xe ô tô mới thành công!");
                break;
            case 3:
                System.out.print("Nhập công suất (HP): ");
                double power = Double.parseDouble(scanner.nextLine().trim());
                service.addVehicle(new Motorcycle(licensePlate, manufacture, yearOfManufacture, owner, power));
                System.out.println("Thêm xe máy thành công!");
                break;
            case 4:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ!");

        }
    }

    private void displayVehicle() {

    }

    private void deleteVehicle() {

    }

    private boolean confirmExit(Scanner scanner) {
        while (true) {
            System.out.print("Are you sure want to exit the program? (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid option. Please try again!");
            }
        }
    }


    private int validateMenuChoice(Scanner scanner, int max) {
        int choice;
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a number between " + 1 + " and " + max + ":");
                    continue;
                }
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + 1 + " and " + max + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again!");
            }
        }
    }
}

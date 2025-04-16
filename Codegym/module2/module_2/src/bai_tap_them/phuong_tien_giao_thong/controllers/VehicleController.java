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
        MenuPrinter.printDisplayVehicleTypeMenu();
        int vehicleType = validateMenuChoice(scanner, 4);
        Vehicle[] vehicles = service.getAllVehicles();
        boolean hasVehicle = false;

        switch (vehicleType) {
            case 1:
                System.out.println("Danh sách xe tải:");
                for (Vehicle vehicle : vehicles) {
                    if (vehicle instanceof Truck) {
                        vehicle.displayInfo();
                        hasVehicle = true;
                    }
                }
                break;
            case 2:
                System.out.println("Danh sách xe ô tô:");
                for (Vehicle vehicle : vehicles) {
                    if (vehicle instanceof Car) {
                        vehicle.displayInfo();
                        hasVehicle = true;
                    }
                }
                break;
            case 3:
                System.out.println("Danh sách xe máy:");
                for (Vehicle vehicle : vehicles) {
                    if (vehicle instanceof Motorcycle) {
                        vehicle.displayInfo();
                        hasVehicle = true;
                    }
                }
                break;
            case 4:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
        }
        if (!hasVehicle) {
            System.out.println("Không có phương tiện nào thuộc loại này!");
        }
    }

    private void deleteVehicle() {

    }

    private boolean confirmExit(Scanner scanner) {
        while (true) {
            System.out.print("Bạn có chắc chắn muốn thoát khỏi chương trình không (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                System.out.println("Không hợp lệ!");
            }
        }
    }


    private int validateMenuChoice(Scanner scanner, int max) {
        int choice;
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Không hợp lệ, vui lòng nhập từ " + 1 + " đến " + max + ":");
                    continue;
                }
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Không hợp lệ, vui lòng nhập từ " + 1 + " đến " + max + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Không hợp lệ!");
            }
        }
    }
}

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
    private static final IVehicleService service = new VehicleService();
    private static final Scanner scanner = new Scanner(System.in);

    public void displayMenu() {
        int choice;
        do {
            MenuPrinter.printMainMenu();
            choice = validateMenuChoice(4);
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
                    if (confirmExit()) {
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
    
    private static void addTruck() {
        System.out.print("Nhập biển kiểm soát: ");
        String licensePlate = scanner.nextLine().trim();
        System.out.print("Nhập hãng sản xuất: ");
        String manufacture = scanner.nextLine().trim();
        System.out.print("Nhập năm sản xuất: ");
        int yearOfManufacture = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhập tên chủ sở hữu: ");
        String owner = scanner.nextLine().trim();
        System.out.print("Nhập tải trọng (tấn): ");
        double loadCapacity = Double.parseDouble(scanner.nextLine().trim());
        service.addVehicle(new Truck(licensePlate, manufacture, yearOfManufacture, owner, loadCapacity));
    }

    private static void addCar() {
        System.out.print("Nhập biển kiểm soát: ");
        String licensePlate = scanner.nextLine().trim();
        System.out.print("Nhập hãng sản xuất: ");
        String manufacture = scanner.nextLine().trim();
        System.out.print("Nhập năm sản xuất: ");
        int yearOfManufacture = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhập tên chủ sở hữu: ");
        String owner = scanner.nextLine().trim();
        System.out.print("Nhập số chỗ ngồi: ");
        int numberOfSeats = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhập kiểu xe (du lịch, xe khách): ");
        String carType = scanner.nextLine().trim();
        service.addVehicle(new Car(licensePlate, manufacture, yearOfManufacture, owner, numberOfSeats, carType));
    }

    private static void addMotorcycle() {
        System.out.print("Nhập biển kiểm soát: ");
        String licensePlate = scanner.nextLine().trim();
        System.out.print("Nhập hãng sản xuất: ");
        String manufacture = scanner.nextLine().trim();
        System.out.print("Nhập năm sản xuất: ");
        int yearOfManufacture = Integer.parseInt(scanner.nextLine().trim());
        System.out.print("Nhập tên chủ sở hữu: ");
        String owner = scanner.nextLine().trim();
        System.out.print("Nhập công suất (HP): ");
        double power = Double.parseDouble(scanner.nextLine().trim());
        service.addVehicle(new Motorcycle(licensePlate, manufacture, yearOfManufacture, owner, power));
    }

    private void addVehicle() {
        MenuPrinter.printAddNewMenu();
        int vehicleType = validateMenuChoice(4);
        switch (vehicleType) {
            case 1:
                addTruck();
                System.out.println("Thêm xe tải mới thành công!");
                break;
            case 2:
                addCar();
                System.out.println("Thêm xe ô tô mới thành công!");
                break;
            case 3:
                addMotorcycle();
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
        int vehicleType = validateMenuChoice(4);
        Vehicle[] vehicles = service.getAllVehicles();
        boolean hasVehicle = false;

        switch (vehicleType) {
            case 1:
                System.out.println("Danh sách xe tải:");
                for (int i = 0; i < vehicles.length; i++) {
                    if (vehicles[i] instanceof Truck) {
                        vehicles[i].displayInfo();
                        hasVehicle = true;
                    }
                }
                break;
            case 2:
                System.out.println("Danh sách xe ô tô:");
                for (int i = 0; i < vehicles.length; i++) {
                    if (vehicles[i] instanceof Car) {
                        vehicles[i].displayInfo();
                        hasVehicle = true;
                    }
                }
                break;
            case 3:
                System.out.println("Danh sách xe máy:");
                for (int i = 0; i < vehicles.length; i++) {
                    if (vehicles[i] instanceof Motorcycle) {
                        vehicles[i].displayInfo();
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
        System.out.print("Nhập biển kiểm soát xe mà bạn muốn xoá: ");
        String licensePlate = scanner.nextLine().trim();
        Vehicle vehicle = service.findByLicensePlate(licensePlate);
        if (vehicle == null) {
            System.out.println("Không tìm thấy phương tiện với biển kiểm soát là: " + licensePlate);
            return;
        }
        System.out.print("Bạn có chắc chắn muốn xoá phương tiện với biển kiểm soát " + licensePlate + " không (Y/N)? ");
        String confirmation = scanner.nextLine().trim().toUpperCase();
        if (confirmation.equals("Y")) {
            service.deleteVehicle(licensePlate);
            System.out.println("Đã xoá phương tiện có biển kiểm soát " + licensePlate + ", Bấm Enter để quay lại!");
            scanner.nextLine();
        } else {
            System.out.println("Huỷ thao tác xoá, quay lại màn hình chính!");
        }

    }

    private boolean confirmExit() {
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


    private int validateMenuChoice(int max) {
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

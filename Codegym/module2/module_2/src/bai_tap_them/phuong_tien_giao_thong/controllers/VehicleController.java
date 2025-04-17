package bai_tap_them.phuong_tien_giao_thong.controllers;

import bai_tap_them.phuong_tien_giao_thong.models.*;
import bai_tap_them.phuong_tien_giao_thong.services.IVehicleService;
import bai_tap_them.phuong_tien_giao_thong.services.VehicleService;
import bai_tap_them.phuong_tien_giao_thong.utils.MenuPrinter;

import java.awt.*;
import java.util.ArrayList;
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

    private CommonFields inputCommonFields() {
        String licensePlate;
        while (true) {
            System.out.print("Nhập biển kiểm soát: ");
            licensePlate = scanner.nextLine().trim();
            if (!licensePlate.isEmpty()) {
                break;
            } else {
                System.out.println("Biển kiểm soát không được để trống.");
            }
        }

        String manufacture;
        while (true) {
            System.out.print("Nhập hãng sản xuất: ");
            manufacture = scanner.nextLine().trim();
            if (!manufacture.isEmpty()) {
                break;
            } else {
                System.out.println("Hãng sản xuất không được để trống.");
            }
        }

        int yearOfManufacture;
        while (true) {
            try {
                System.out.print("Nhập năm sản xuất: ");
                yearOfManufacture = Integer.parseInt(scanner.nextLine().trim());
                if (yearOfManufacture > 0) {
                    break;
                } else {
                    System.out.println("Năm sản xuất phải lớn hơn 0.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            }
        }

        String owner;
        while (true) {
            System.out.print("Nhập tên chủ sở hữu: ");
            owner = scanner.nextLine().trim();
            if (!owner.isEmpty()) {
                break;
            } else {
                System.out.println("Tên chủ sở hữu không được để trống.");
            }
        }

        return new CommonFields(licensePlate, manufacture, yearOfManufacture, owner);
    }

    private void addVehicle() {
        MenuPrinter.printAddNewMenu();
        int vehicleType = validateMenuChoice(4);
        Vehicle vehicle = null;

        CommonFields commonFields = inputCommonFields();
        switch (vehicleType) {
            case 1:
                vehicle = new Truck(commonFields.getLicensePlate(), commonFields.getManufacture(),
                        commonFields.getYearOfManufacture(), commonFields.getOwner(), 0);
                break;
            case 2:
                vehicle = new Car(commonFields.getLicensePlate(), commonFields.getManufacture(),
                        commonFields.getYearOfManufacture(), commonFields.getOwner(), 0, null);
                break;
            case 3:
                vehicle = new Motorcycle(commonFields.getLicensePlate(), commonFields.getManufacture(),
                        commonFields.getYearOfManufacture(), commonFields.getOwner(), 0);
                break;
            case 4:
                return;
            default:
                System.out.println("Lựa chọn không hợp lệ!");
                return;
        }

        vehicle.inputSpecificFields(scanner);
        service.addVehicle(vehicle);
        System.out.println("Thêm phương tiện mới thành công!");
    }

    private void displayVehicle() {
        MenuPrinter.printDisplayVehicleTypeMenu();
        int vehicleType = validateMenuChoice(4);
        ArrayList<Vehicle> vehicles = service.getAllVehicles();
        boolean hasVehicle = false;

        switch (vehicleType) {
            case 1:
                System.out.println("Danh sách xe tải:");
                for (int i = 0; i < vehicles.size(); i++) {
                    if (vehicles.get(i) instanceof Truck) {
                        vehicles.get(i).displayInfo();
                        hasVehicle = true;
                    }
                }
                break;
            case 2:
                System.out.println("Danh sách xe ô tô:");
                for (int i = 0; i < vehicles.size(); i++) {
                    if (vehicles.get(i) instanceof Car) {
                        vehicles.get(i).displayInfo();
                        hasVehicle = true;
                    }
                }
                break;
            case 3:
                System.out.println("Danh sách xe máy:");
                for (int i = 0; i < vehicles.size(); i++) {
                    if (vehicles.get(i) instanceof Motorcycle) {
                        vehicles.get(i).displayInfo();
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

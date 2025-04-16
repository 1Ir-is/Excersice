package bai_tap_them.phuong_tien_giao_thong.models;

import java.util.Scanner;

public class Truck extends Vehicle {
    private double loadCapacity;

    public Truck(String licensePlate, String manufacturer, int yearOfManufacture, String owner,
                 double loadCapacity) {
        super(licensePlate, manufacturer, yearOfManufacture, owner);
        this.loadCapacity = loadCapacity;
    }

    public double getLoadCapacity() {
        return loadCapacity;
    }

    public void setLoadCapacity(double loadCapacity) {
        this.loadCapacity = loadCapacity;
    }

    @Override
    public void inputSpecificFields(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập trọng tải (tấn): ");
                this.loadCapacity = Double.parseDouble(scanner.nextLine().trim());
                if (this.loadCapacity <= 0) {
                    throw new IllegalArgumentException("Trọng tải phải lớn hơn 0.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số thực hợp lệ.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }
    @Override
    public void displayInfo() {
        System.out.printf("Xe tải [Biển kiểm soát: %s, Hãng sản xuất: %s, Năm sản xuất: %d, Chủ sở hữu: %s, Trọng tải: %.2f tấn]%n",
                getLicensePlate(), getManufacturer(), getYearOfManufacture(), getOwner(), loadCapacity);
    }
}

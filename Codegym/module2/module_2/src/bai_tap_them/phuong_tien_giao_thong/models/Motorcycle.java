package bai_tap_them.phuong_tien_giao_thong.models;

import java.util.Scanner;

public class Motorcycle extends Vehicle {

    private double power;

    public Motorcycle(String licensePlate, String manufacturer, int yearOfManufacture, String owner,
                      double power) {
        super(licensePlate, manufacturer, yearOfManufacture, owner);
        this.power = power;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    @Override
    public void inputSpecificFields(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập công suất (HP): ");
                this.power = Double.parseDouble(scanner.nextLine().trim());
                if (this.power <= 0) {
                    throw new IllegalArgumentException("Công suất phải lớn hơn 0.");
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
        System.out.printf("Xe máy [Biển kiểm soát: %s, Hãng sản xuất: %s, Năm sản xuất: %d, Chủ sở hữu: %s, Công suất: %.2f HP]%n",
                getLicensePlate(), getManufacturer(), getYearOfManufacture(), getOwner(), power);
    }
}

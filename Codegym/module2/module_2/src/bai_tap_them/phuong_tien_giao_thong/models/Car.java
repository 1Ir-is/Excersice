package bai_tap_them.phuong_tien_giao_thong.models;

import java.util.Scanner;

public class Car extends Vehicle {

    private int numberOfSeats;
    private String carType;

    public Car(String licensePlate, String manufacturer, int yearOfManufacture, String owner,
               int numberOfSeat, String carType) {
        super(licensePlate, manufacturer, yearOfManufacture, owner);
        this.numberOfSeats = numberOfSeat;
        this.carType = carType;
    }

    public int getNumberOfSeats() {
        return numberOfSeats;
    }

    public void setNumberOfSeats(int numberOfSeats) {
        this.numberOfSeats = numberOfSeats;
    }

    public String getCarType() {
        return carType;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    @Override
    public void inputSpecificFields(Scanner scanner) {
        while (true) {
            try {
                System.out.print("Nhập số chỗ ngồi: ");
                this.numberOfSeats = Integer.parseInt(scanner.nextLine().trim());
                if (this.numberOfSeats <= 0) {
                    throw new IllegalArgumentException("Số chỗ ngồi phải lớn hơn 0.");
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Vui lòng nhập một số nguyên hợp lệ.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        while (true) {
            System.out.print("Nhập loại xe: ");
            this.carType = scanner.nextLine().trim();
            if (!this.carType.isEmpty()) {
                break;
            } else {
                System.out.println("Loại xe không được để trống.");
            }
        }
    }

    @Override
    public void displayInfo() {
        System.out.printf("Xe ô tô [Biển kiểm soát: %s, hãng sản xuất: %s, năm sản xuất: %d, chủ sở hữu: %s, số chỗ ngồi: %d, loại xe: %s]%n",
                getLicensePlate(), getManufacturer(), getYearOfManufacture(), getOwner(), numberOfSeats, carType);
    }

    @Override
    public String toCSV() {
        return "Car," + super.toCSV() + "," + numberOfSeats + "," + carType;
    }
}

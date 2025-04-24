package bai_tap_them.phuong_tien_giao_thong.models;

import java.util.Scanner;

public abstract class Vehicle {
    private String licensePlate;
    private String manufacturer;
    private int yearOfManufacture;
    private String owner;

    public Vehicle(String licensePlate, String manufacturer, int yearOfManufacture, String owner) {
        this.licensePlate = licensePlate;
        this.manufacturer = manufacturer;
        this.yearOfManufacture = yearOfManufacture;
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public void setYearOfManufacture(int yearOfManufacture) {
        this.yearOfManufacture = yearOfManufacture;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }
    
    public abstract void inputSpecificFields(Scanner scanner);

    public abstract void displayInfo();

    public String toCSV() {
        return licensePlate + "," + manufacturer + "," + yearOfManufacture + "," + owner;
    }
}

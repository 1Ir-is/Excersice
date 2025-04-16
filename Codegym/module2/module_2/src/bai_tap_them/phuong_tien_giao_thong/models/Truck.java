package bai_tap_them.phuong_tien_giao_thong.models;

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
    public void displayInfo() {
        System.out.printf("Truck [License Plate: %s, Manufacturer: %s, Year: %d, Owner: %s, Load Capacity: %.2f tons]%n",
                getLicensePlate(), getManufacturer(), getYearOfManufacture(), getOwner(), loadCapacity);
    }
}

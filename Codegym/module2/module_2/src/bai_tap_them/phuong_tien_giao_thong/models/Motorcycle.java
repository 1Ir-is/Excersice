package bai_tap_them.phuong_tien_giao_thong.models;

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
    public void displayInfo() {
        System.out.printf("Motorcycle [License Plate: %s, Manufacturer: %s, Year: %d, Owner: %s, Power: %.2f HP]%n",
                getLicensePlate(), getManufacturer(), getYearOfManufacture(), getOwner(), power);
    }
}

package bai_tap_them.phuong_tien_giao_thong.models;

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
    public void displayInfo() {
        System.out.printf("Car [License Plate: %s, Manufacturer: %s, Year: %d, Owner: %s, Seats: %d, Type: %s]%n",
                getLicensePlate(), getManufacturer(), getYearOfManufacture(), getOwner(), numberOfSeats, carType);
    }
}

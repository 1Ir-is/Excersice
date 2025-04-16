package bai_tap_them.phuong_tien_giao_thong.models;

public class CommonFields {
    private String licensePlate;
    private String manufacture;
    private int yearOfManufacture;
    private String owner;

    public CommonFields(String licensePlate, String manufacture, int yearOfManufacture, String owner) {
        this.licensePlate = licensePlate;
        this.manufacture = manufacture;
        this.yearOfManufacture = yearOfManufacture;
        this.owner = owner;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public String getManufacture() {
        return manufacture;
    }

    public int getYearOfManufacture() {
        return yearOfManufacture;
    }

    public String getOwner() {
        return owner;
    }
}
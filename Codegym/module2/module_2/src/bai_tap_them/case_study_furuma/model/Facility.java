package bai_tap_them.case_study_furuma.model;

public abstract class Facility {
    private String id;
    private String name;
    private double area;
    private double rentalCost;
    private int maxPeople;
    private String rentalType;

    public Facility(String id, String name, double area, double rentalCost, int maxPeople, String rentalType) {
        this.id = id;
        this.name = name;
        this.area = area;
        this.rentalCost = rentalCost;
        this.maxPeople = maxPeople;
        this.rentalType = rentalType;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getArea() {
        return area;
    }

    public void setArea(double area) {
        this.area = area;
    }

    public double getRentalCost() {
        return rentalCost;
    }

    public void setRentalCost(double rentalCost) {
        this.rentalCost = rentalCost;
    }

    public int getMaxPeople() {
        return maxPeople;
    }

    public void setMaxPeople(int maxPeople) {
        this.maxPeople = maxPeople;
    }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public abstract String getDetails();
}

package bai_tap_them.case_study_furuma.models;

public class Villa extends Facility {

    private String roomStandard;
    private double poolArea;
    private int numberOfFloor;

    public Villa(String id, String name, double area, double rentalCost, int maxPeople, String rentalType,
                 String roomStandard, double poolArea, int numberOfFloor) {
        super(id, name, area, rentalCost, maxPeople, rentalType);
        this.roomStandard = roomStandard;
        this.poolArea = poolArea;
        this.numberOfFloor = numberOfFloor;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public double getPoolArea() {
        return poolArea;
    }

    public void setPoolArea(double poolArea) {
        this.poolArea = poolArea;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    @Override
    public String getDetails() {
        return String.join("\n",
                "Villa Details:",
                "ID: " + getId(),
                "Name: " + getName(),
                "Area: " + getArea(),
                "Rental Cost: " + getRentalCost(),
                "Max People: " + getMaxPeople(),
                "Rental Type: " + getRentalType(),
                "Room Standard: " + roomStandard,
                "Pool Area: " + poolArea,
                "Number of Floors: " + numberOfFloor
        );
    }

    @Override
    public String toCSV() {
        return String.join(",",
                "Villa",
                getId(),
                getName(),
                String.valueOf(getArea()),
                String.valueOf(getRentalCost()),
                String.valueOf(getMaxPeople()),
                getRentalType(),
                roomStandard,
                String.valueOf(poolArea),
                String.valueOf(numberOfFloor)
        );
    }
}

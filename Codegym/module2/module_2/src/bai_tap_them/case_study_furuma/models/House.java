package bai_tap_them.case_study_furuma.models;

public class House extends Facility {

    private String roomStandard;
    private int numberOfFloor;

    public House(String id, String name, double area, double rentalCost, int maxPeople, String rentalType,
                 String roomStandard, int numberOfFloor) {
        super(id, name, area, rentalCost, maxPeople, rentalType);
        this.roomStandard = roomStandard;
        this.numberOfFloor = numberOfFloor;
    }

    public String getRoomStandard() {
        return roomStandard;
    }

    public void setRoomStandard(String roomStandard) {
        this.roomStandard = roomStandard;
    }

    public int getNumberOfFloor() {
        return numberOfFloor;
    }

    public void setNumberOfFloor(int numberOfFloor) {
        this.numberOfFloor = numberOfFloor;
    }

    @Override
    public String getDetails() {
        return "House: " + getName() + ", Room Standard: " + roomStandard + ", Number of floor: " + numberOfFloor;
    }
}

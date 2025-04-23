package bai_tap_them.case_study_furuma.models;

public class Room extends Facility {
    private String freeService;

    public Room(String id, String name, double area, double rentalCost, int maxPeople, String rentalType,
                String freeService) {
        super(id, name, area, rentalCost, maxPeople, rentalType);
        this.freeService = freeService;
    }

    public String getFreeService() {
        return freeService;
    }

    public void setFreeService(String freeService) {
        this.freeService = freeService;
    }

    @Override
    public String getDetails() {
        return "Room{" +
                "id='" + getId() + '\'' +
                ", name='" + getName() + '\'' +
                ", area=" + getArea() +
                ", rentalCost=" + getRentalCost() +
                ", maxPeople=" + getMaxPeople() +
                ", rentalType='" + getRentalType() + '\'' +
                ", freeService='" + freeService + '\'' +
                '}';
    }


}

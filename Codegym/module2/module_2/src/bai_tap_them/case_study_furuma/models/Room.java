package bai_tap_them.case_study_furuma.models;

public class Room extends Facility {
    private String freeService;

    public Room(String id, String name, double area, double rentalCost, int maxPeople, String rentalType,
                String freeService) {
        super(id, name, area, rentalCost, maxPeople, rentalType);
        this.freeService = freeService;
    }

    @Override
    public String getDetails() {
        return "Room: " + getName() + "Free service: " + freeService;
    }
}

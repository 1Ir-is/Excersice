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
        return String.join("\n",
                "Room Details:",
                "ID: " + getId(),
                "Name: " + getName(),
                "Area: " + getArea(),
                "Rental Cost: " + getRentalCost(),
                "Max People: " + getMaxPeople(),
                "Rental Type: " + getRentalType(),
                "Free Service: " + freeService
        );
    }

    @Override
    public String toCSV() {
        return String.join(",",
                "Room",
                getId(),
                getName(),
                String.valueOf(getArea()),
                String.valueOf(getRentalCost()),
                String.valueOf(getMaxPeople()),
                getRentalType(),
                freeService
        );
    }


}

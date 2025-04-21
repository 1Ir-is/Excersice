package bai_tap_them.case_study_furuma.services.facility;

import bai_tap_them.case_study_furuma.models.Facility;
import bai_tap_them.case_study_furuma.models.House;
import bai_tap_them.case_study_furuma.models.Room;
import bai_tap_them.case_study_furuma.models.Villa;
import bai_tap_them.case_study_furuma.repositories.facility.IFacilityRepository;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class FacilityService implements IFacilityService {

    private final IFacilityRepository facilityRepository;
    private final Scanner scanner = new Scanner(System.in);

    public FacilityService(IFacilityRepository repository) {
        this.facilityRepository = repository;
    }


    @Override
    public void displayFacilities() {
        ArrayList<Facility> facilities = facilityRepository.findAll();
        if (facilities.isEmpty()) {
            System.out.println("No facilities available.");
            return;
        }
        System.out.println();
        System.out.printf("%-10s %-20s %-8s %-12s %-12s %-15s %-15s %-8s %-10s %-20s\n",
                "ID", "Name", "Area", "RentalCost", "MaxPeople", "RentalType", "RoomStandard", "Floors", "PoolArea", "FreeService");
        System.out.println("---------------------------------------------------------------------------------------------------------------------------------------");

        for (int i = 0; i < facilities.size(); i++) {
            Facility f = facilities.get(i);

            if (f instanceof Villa v) {
                System.out.printf("%-10s %-20s %-8.1f %-12.1f %-12d %-15s %-15s %-8d %-10s %-20s\n",
                        v.getId(), v.getName(), v.getArea(), v.getRentalCost(), v.getMaxPeople(),
                        v.getRentalType(), v.getRoomStandard(), v.getNumberOfFloor(), v.getPoolArea() + " m²", "-");
            } else if (f instanceof House h) {
                System.out.printf("%-10s %-20s %-8.1f %-12.1f %-12d %-15s %-15s %-8d %-10s %-20s\n",
                        h.getId(), h.getName(), h.getArea(), h.getRentalCost(), h.getMaxPeople(),
                        h.getRentalType(), h.getRoomStandard(), h.getNumberOfFloor(), "-", "-");
            } else if (f instanceof Room r) {
                System.out.printf("%-10s %-20s %-8.1f %-12.1f %-12d %-15s %-15s %-8s %-10s %-20s\n",
                        r.getId(), r.getName(), r.getArea(), r.getRentalCost(), r.getMaxPeople(),
                        r.getRentalType(), "-", "-", "-", r.getFreeService());
            }
        }
        System.out.println();
    }


    @Override
    public void addNewFacility() {

    }

    @Override
    public void displayFacilitiesNeedingMaintenance() {

    }

    @Override
    public void display() {
        displayFacilities();
    }

    @Override
    public void add() {

    }

    @Override
    public void edit() {

    }
}

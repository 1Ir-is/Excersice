package bai_tap_them.case_study_furuma.repositories.facility;

import bai_tap_them.case_study_furuma.models.*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class FacilityRepository implements IFacilityRepository {

    private static final LinkedHashMap<Facility, Integer> facilities = new LinkedHashMap<>();

    static {
        facilities.put(new Villa("SVVL-0001", "Luxury Villa", 100, 5000, 10, "Day", "VIP", 50, 2), 0);
        facilities.put(new House("SVHO-0001", "Family House", 80, 3000, 8, "Month", "Standard", 2), 0);
        facilities.put(new Room("SVRO-0001", "Single Room", 30, 1000, 2, "Hour", "Free Breakfast"), 0);
    }

    @Override

    public void add(Facility facility) {

    }

    @Override
    public LinkedHashMap<Facility, Integer> findFacilitiesNeedingMaintenance() {
        return null;
    }

    @Override
    public ArrayList<Facility> findAll() {
        return new ArrayList<>(facilities.keySet());
    }
}

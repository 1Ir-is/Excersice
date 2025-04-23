package bai_tap_them.case_study_furuma.repositories.facility;

import bai_tap_them.case_study_furuma.models.*;

import java.util.*;

public class FacilityRepository implements IFacilityRepository {

    private static final LinkedHashMap<Facility, Integer> facilities = new LinkedHashMap<>();

    static {
        facilities.put(new Villa("SVVL-0001", "Luxury Villa", 100, 5000, 10, "Day", "VIP", 50, 2), 6);
        facilities.put(new House("SVHO-0001", "Family House", 80, 3000, 8, "Month", "Standard", 2), 0);
        facilities.put(new Room("SVRO-0001", "Single Room", 30, 1000, 2, "Hour", "Free Breakfast"), 0);
    }

    @Override
    public void add(Facility facility) {
        facilities.put(facility, 0);
    }

    @Override
    public Map<Facility, Integer> findFacilitiesNeedingMaintenance() {
        Map<Facility, Integer> needingMaintenance = new LinkedHashMap<>();
        for (Map.Entry<Facility, Integer> entry : facilities.entrySet()) {
            if (entry.getValue() >= 5) {
                needingMaintenance.put(entry.getKey(), entry.getValue());
            }
        }
        return needingMaintenance;
    }

    @Override
    public Map<Facility, Integer> findAll() {
        return facilities;
    }
}

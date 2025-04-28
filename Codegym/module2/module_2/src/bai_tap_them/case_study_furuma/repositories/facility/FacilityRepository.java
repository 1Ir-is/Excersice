package bai_tap_them.case_study_furuma.repositories.facility;

import bai_tap_them.case_study_furuma.models.*;
import bai_tap_them.case_study_furuma.utils.SaveFileUtils;

import java.util.*;

public class FacilityRepository implements IFacilityRepository {

    private static final LinkedHashMap<Facility, Integer> facilities = new LinkedHashMap<>();
    private static final String FACILITIES_FILE = "bai_tap_them/case_study_furuma/data/facilities.csv";

    static {
        loadFromCSV();
    }

    @Override
    public void add(Facility facility) {
        facilities.put(facility, 0);
        saveToCSV();
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

    @Override
    public Facility findById(String id) {
        for (Facility facility : facilities.keySet()) {
            if (facility.getId().equals(id)) {
                return facility;
            }
        }
        return null;
    }

    @Override
    public List<Facility> findAllByType(String type) {
        List<Facility> filteredFacilities = new ArrayList<>();
        for (Facility facility : facilities.keySet()) {
            if (type.equalsIgnoreCase("Villa") && facility instanceof Villa) {
                filteredFacilities.add(facility);
            } else if (type.equalsIgnoreCase("House") && facility instanceof House) {
                filteredFacilities.add(facility);
            } else if (type.equalsIgnoreCase("Room") && facility instanceof Room) {
                filteredFacilities.add(facility);
            }
        }
        return filteredFacilities;
    }

    private static void saveToCSV() {
        List<String> lines = new ArrayList<>();
        for (Map.Entry<Facility, Integer> entry : facilities.entrySet()) {
            Facility facility = entry.getKey();
            int usageCount = entry.getValue();
            lines.add(facility.toCSV() + "," + usageCount);
        }
        SaveFileUtils.writeToFile(FACILITIES_FILE, lines, false);
    }

    private static void loadFromCSV() {
        List<String> lines = SaveFileUtils.readFromFile(FACILITIES_FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts.length < 2) continue;

            String type = parts[0];
            Facility facility = null;

            switch (type) {
                case "Villa":
                    facility = new Villa(parts[1], parts[2], Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6],
                            parts[7], Double.parseDouble(parts[8]), Integer.parseInt(parts[9]));
                    break;
                case "House":
                    facility = new House(parts[1], parts[2], Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6],
                            parts[7], Integer.parseInt(parts[8]));
                    break;
                case "Room":
                    facility = new Room(parts[1], parts[2], Double.parseDouble(parts[3]),
                            Double.parseDouble(parts[4]), Integer.parseInt(parts[5]), parts[6],
                            parts[7]);
                    break;
            }

            if (facility != null) {
                facilities.put(facility, Integer.parseInt(parts[parts.length - 1]));
            }
        }
    }
}

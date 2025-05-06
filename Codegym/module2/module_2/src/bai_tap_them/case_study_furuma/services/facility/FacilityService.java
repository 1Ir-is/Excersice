package bai_tap_them.case_study_furuma.services.facility;

import bai_tap_them.case_study_furuma.models.Facility;
import bai_tap_them.case_study_furuma.models.House;
import bai_tap_them.case_study_furuma.models.Room;
import bai_tap_them.case_study_furuma.models.Villa;
import bai_tap_them.case_study_furuma.repositories.facility.IFacilityRepository;
import bai_tap_them.case_study_furuma.utils.MenuPrinter;
import bai_tap_them.case_study_furuma.views.CommonView;
import bai_tap_them.case_study_furuma.views.HouseView;
import bai_tap_them.case_study_furuma.views.RoomView;
import bai_tap_them.case_study_furuma.views.VillaView;

import java.util.*;

public class FacilityService implements IFacilityService {

    private final IFacilityRepository facilityRepository;

    public FacilityService(IFacilityRepository repository) {
        this.facilityRepository = repository;
    }


    @Override
    public void displayFacilities() {
        Map<Facility, Integer> facilities = facilityRepository.findAll();

        if (facilities.isEmpty()) {
            System.out.println("No facilities available.");
            return;
        }

        System.out.println("Facility List:");
        for (Map.Entry<Facility, Integer> entry : facilities.entrySet()) {
            Facility facility = entry.getKey();
            int usageCount = entry.getValue();

            System.out.println("--------------------------------------------------");
            System.out.println("ID: " + facility.getId());
            System.out.println("Name: " + facility.getName());
            System.out.println("Area: " + facility.getArea() + " m²");
            System.out.println("Rental Cost: " + facility.getRentalCost());
            System.out.println("Max People: " + facility.getMaxPeople());
            System.out.println("Rental Type: " + facility.getRentalType());

            if (facility instanceof Villa villa) {
                System.out.println("Room Standard: " + villa.getRoomStandard());
                System.out.println("Number of Floors: " + villa.getNumberOfFloor());
                System.out.println("Pool Area: " + villa.getPoolArea() + " m²");
            } else if (facility instanceof House house) {
                System.out.println("Room Standard: " + house.getRoomStandard());
                System.out.println("Number of Floors: " + house.getNumberOfFloor());
            } else if (facility instanceof Room room) {
                System.out.println("Free Service: " + room.getFreeService());
            }

            System.out.println("Usage Count: " + usageCount);
        }
        System.out.println("--------------------------------------------------");
    }



    @Override
    public void addNewFacility() {
        MenuPrinter.printAddNewFacilityMenu();
        int choice = CommonView.getChoice(4);
        switch (choice) {
            case 1:
                addNewVilla();
                break;
            case 2:
                addNewHouse();
                break;
            case 3:
                addNewRoom();
                break;
            case 4:
                return;
            default:
                System.out.println("Invalid");
        }
    }

    private void addNewVilla() {
        String id = VillaView.inputId("SVVL-\\d{4}", "Invalid ID format!");
        String name = VillaView.inputName();
        double area = VillaView.inputArea();
        double rentalCost = VillaView.inputRentalCost();
        int maxPeople = VillaView.inputMaxPeople();
        String rentalType = VillaView.inputRentalType();
        String roomStandard = VillaView.inputRoomStandard();
        double poolArea = VillaView.inputPoolArea();
        int numberOfFloors = VillaView.inputNumberOfFloors();

        Villa villa = new Villa(id, name, area, rentalCost, maxPeople, rentalType, roomStandard, poolArea, numberOfFloors);
        facilityRepository.add(villa);
        System.out.println("Villa added successfully!");
        System.out.println();
    }

    private void addNewHouse() {
        String id = HouseView.inputId("SVHO-\\d{4}", "Invalid ID format!");
        String name = HouseView.inputName();
        double area = HouseView.inputArea();
        double rentalCost = HouseView.inputRentalCost();
        int maxPeople = HouseView.inputMaxPeople();
        String rentalType = HouseView.inputRentalType();
        String roomStandard = HouseView.inputRoomStandard();
        int numberOfFloors = HouseView.inputNumberOfFloors();

        House house = new House(id, name, area, rentalCost, maxPeople, rentalType, roomStandard, numberOfFloors);
        facilityRepository.add(house);
        System.out.print("House added successfully!");
        System.out.println();
    }

    private void addNewRoom() {
        String id = RoomView.inputId("SVRO-\\d{4}", "Invalid ID format!");
        String name = RoomView.inputName();
        double area = RoomView.inputArea();
        double rentalCost = RoomView.inputRentalCost();
        int maxPeople = RoomView.inputMaxPeople();
        String rentalType = RoomView.inputRentalType();
        String freeService = RoomView.inputFreeService();

        Room room = new Room(id, name, area, rentalCost, maxPeople, rentalType, freeService);
        facilityRepository.add(room);
        System.out.println("Room added successfully!");
        System.out.println();
    }

    @Override
    public void display() {
        displayFacilities();
    }

    @Override
    public void add() {
        addNewFacility();
    }

    @Override
    public void edit() {

    }

    @Override
    public void displayFacilitiesNeedingMaintenance() {
        Map<Facility, Integer> needingMaintenance = facilityRepository.findFacilitiesNeedingMaintenance();

        if (needingMaintenance.isEmpty()) {
            System.out.println("No facilities need maintenance.");
        } else {
            List<Map.Entry<Facility, Integer>> entryList = new ArrayList<>(needingMaintenance.entrySet());

            for (int i = 0; i < entryList.size(); i++) {
                Map.Entry<Facility, Integer> entry = entryList.get(i);
                System.out.println(entry.getKey() + ", Usage Count: " + entry.getValue());
            }
        }
    }
}

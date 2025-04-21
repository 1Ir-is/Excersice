package bai_tap_them.case_study_furuma.services.facility;

import bai_tap_them.case_study_furuma.models.Facility;
import bai_tap_them.case_study_furuma.models.House;
import bai_tap_them.case_study_furuma.models.Room;
import bai_tap_them.case_study_furuma.models.Villa;
import bai_tap_them.case_study_furuma.repositories.facility.IFacilityRepository;
import bai_tap_them.case_study_furuma.utils.MenuPrinter;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;
import bai_tap_them.case_study_furuma.view.CommonView;

import javax.swing.*;
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
        System.out.print("Enter ID (format: SVVL-YYYY): ");
        String id = ValidationUtils.validateInput("SVVL-\\d{4}", "Invalid ID format!");
        System.out.print("Enter name: ");
        String name = ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Invalid name format!");
        System.out.print("Enter area (>30): ");
        double area = ValidationUtils.validateDouble(30);
        System.out.print("Enter rental cost (>0): ");
        double rentalCost = ValidationUtils.validateDouble(0);
        System.out.print("Enter max people (1-19): ");
        int maxPeople = ValidationUtils.validateInt(1, 19);
        System.out.print("Enter rental type: ");
        String rentalType = scanner.nextLine();
        System.out.print("Enter room standard: ");
        String roomStandard = scanner.nextLine();
        System.out.print("Enter pool area (>30): ");
        double poolArea = ValidationUtils.validateDouble(30);
        System.out.print("Enter number of floors (>0): ");
        int numberOfFloors = ValidationUtils.validateInt(1, Integer.MAX_VALUE);

        Villa villa = new Villa(id, name, area, rentalCost, maxPeople, rentalType, roomStandard, poolArea, numberOfFloors);
        facilityRepository.add(villa);
        System.out.println("Villa added successfully!");
        System.out.println();
    }

    private void addNewHouse() {
        System.out.print("Enter ID (format: SVHO-YYYY): ");
        String id = ValidationUtils.validateInput("SVHO-\\d{4}", "Invalid Id format. Please try again!");
        System.out.print("Enter name: ");
        String name = ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Invalid name format!");
        System.out.print("Enter area (>30): ");
        double area = ValidationUtils.validateDouble(30);
        System.out.print("Enter rental cost (>0): ");
        double rentalCost = ValidationUtils.validateDouble(0);
        System.out.print("Enter max people (1-19): ");
        int maxPeople = ValidationUtils.validateInt(1, 19);
        System.out.print("Enter rental type: ");
        String rentalType = scanner.nextLine();
        System.out.print("Enter room standard: ");
        String roomStandard = ValidationUtils.validateNonNumericInput("Cannot contain number. Please try again!");
        System.out.print("Enter number of floor (>0): ");
        int numberOfFloors = ValidationUtils.validateInt(1, Integer.MAX_VALUE);

        House house = new House(id, name, area, rentalCost, maxPeople, rentalType, roomStandard, numberOfFloors);
        facilityRepository.add(house);
        System.out.print("House added successfully!");
        System.out.println();
    }

    private void addNewRoom() {
        System.out.print("Enter ID (format: SVRO-YYYY): ");
        String id = ValidationUtils.validateInput("SVRO-\\d{4}", "Invalid Id format. Please try again!");
        System.out.print("Enter name: ");
        String name = ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Invalid name format!");
        System.out.print("Enter area (>30): ");
        double area = ValidationUtils.validateDouble(30);
        System.out.print("Enter rental cost (>0): ");
        double rentalCost = ValidationUtils.validateDouble(0);
        System.out.print("Enter max people (1-19): ");
        int maxPeople = ValidationUtils.validateInt(1, 19);
        System.out.print("Enter rental type: ");
        String rentalType = scanner.nextLine();
        System.out.print("Enter free service: ");
        String freeService = scanner.nextLine();

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

    }
}

package bai_tap_them.case_study_furuma.services.contract;

import bai_tap_them.case_study_furuma.models.*;
import bai_tap_them.case_study_furuma.repositories.booking.IBookingRepository;
import bai_tap_them.case_study_furuma.repositories.contract.IContractRepository;
import bai_tap_them.case_study_furuma.repositories.facility.IFacilityRepository;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.util.*;

public class ContractService implements IContractService {

    private final IBookingRepository bookingRepository;
    private final IFacilityRepository facilityRepository;
    private final IContractRepository contractRepository;

    public ContractService(IBookingRepository bookingRepository, IFacilityRepository facilityRepository, IContractRepository contractRepository) {
        this.bookingRepository = bookingRepository;
        this.facilityRepository = facilityRepository;
        this.contractRepository = contractRepository;
    }


    @Override
    public void display() {
        List<Contract> contracts = contractRepository.findAll();
        if (contracts.isEmpty()) {
            System.out.println("No contracts found!");
            return;
        }

        System.out.println("======== Customer List ========");
        for (Contract contract : contracts) {
            System.out.println("Contract number: " + contract.getContractNumber());
            System.out.println("Booking ID: " + contract.getBookingId());
            System.out.println("Deposit: " + contract.getDeposit());
            System.out.println("Total Amount: " + contract.getTotalPayment());
            System.out.println();
        }
        System.out.println("===============================");
        System.out.println();
    }

    @Override
    public void add() {
        Queue<Booking> bookings = new LinkedList<>(bookingRepository.findAll());

        List<Booking> eligibleBookings = new ArrayList<>();
        while (!bookings.isEmpty()) {
            Booking booking = bookings.poll();
            Facility facility = facilityRepository.findById(booking.getFacilityId());
            if (facility != null && (facility instanceof Villa || facility instanceof House) && !booking.isContracted()) {
                eligibleBookings.add(booking);
            }
        }

        if (eligibleBookings.isEmpty()) {
            System.out.println("No eligible bookings found for contract creation (only Villa and House are allowed).");
            return;
        }

        System.out.println("Eligible Booking: ");
        for (int i = 0; i < eligibleBookings.size(); i++) {
            Booking booking = eligibleBookings.get(i);
            Facility facility = facilityRepository.findById(booking.getFacilityId());
            String facilityInfo = (facility != null) ? facility.getId() + " - " + facility.getName() : "Unknown Facility";
            System.out.println((i + 1) + ". Booking Id: " + booking.getBookingId() + " | Facility: " + facilityInfo);
        }

        Scanner scanner = new Scanner(System.in);
        int selectedIndex;
        Booking selectedBooking = null;

        do {
            System.out.print("Select booking number to create contract: ");
            try {
                selectedIndex = Integer.parseInt(scanner.nextLine());
                if (selectedIndex >= 1 && selectedIndex <= eligibleBookings.size()) {
                    selectedBooking = eligibleBookings.get(selectedIndex - 1);
                    break;
                } else {
                    System.out.println("Invalid selection! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Enter a number.");
            }
        } while (true);
        String contractNumber;
        do {
            System.out.print("Enter contract number: ");
            contractNumber = scanner.nextLine();
        } while (!ValidationUtils.validateNotEmpty(contractNumber, "Cannot be empty!"));

        System.out.print("Enter deposit: ");
        double deposit = Double.parseDouble(scanner.nextLine());

        System.out.print("Enter total payment: ");
        double totalPayment = Double.parseDouble(scanner.nextLine());

        Contract contract = new Contract(contractNumber, selectedBooking.getBookingId(), deposit, totalPayment);
        contractRepository.add(contract);
        selectedBooking.setContracted(true);
        bookingRepository.update(selectedBooking);
        System.out.println("Contract created successfully!");

    }

    @Override
    public void edit() {
        List<Contract> contracts = contractRepository.findAll();
        if (contracts.isEmpty()) {
            System.out.println("No contracts found to edit!");
            return;
        }

        System.out.println("======== Contract List ========");
        for (int i = 0; i < contracts.size(); i++) {
            Contract contract = contracts.get(i);
            System.out.println((i + 1) + ". Contract number: " + contract.getContractNumber());
        }
        System.out.println("===============================");

        Scanner scanner = new Scanner(System.in);
        int selectedIndex;
        Contract selectedContract = null;

        do {
            System.out.print("Select contract number to edit: ");
            try {
                selectedIndex = Integer.parseInt(scanner.nextLine());
                if (selectedIndex >= 1 && selectedIndex <= contracts.size()) {
                    selectedContract = contracts.get(selectedIndex - 1);
                    break;
                } else {
                    System.out.println("Invalid selection! Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        System.out.println("Editing Contract: " + selectedContract.getContractNumber());
        System.out.print("Enter new deposit (current is: " + selectedContract.getDeposit() + "): ");
        double newDeposit = Double.parseDouble(scanner.nextLine());
        System.out.print("Enter new total payment (current is: " + selectedContract.getTotalPayment() + "): ");
        double newTotalPayment = Double.parseDouble(scanner.nextLine());

        selectedContract.setDeposit(newDeposit);
        selectedContract.setTotalPayment(newTotalPayment);

        contractRepository.update(selectedContract);
        System.out.println("Contract updated successfully!");
    }
}

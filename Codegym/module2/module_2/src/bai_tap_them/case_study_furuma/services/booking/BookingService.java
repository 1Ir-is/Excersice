package bai_tap_them.case_study_furuma.services.booking;

import bai_tap_them.case_study_furuma.models.Booking;
import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.models.Facility;
import bai_tap_them.case_study_furuma.models.House;
import bai_tap_them.case_study_furuma.repositories.booking.IBookingRepository;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;
import bai_tap_them.case_study_furuma.repositories.facility.IFacilityRepository;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;

import java.security.spec.EdECPoint;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.TreeSet;

public class BookingService implements IBookingService {
    private final IBookingRepository bookingRepository;
    private final ICustomerRepository customerRepository;
    private final IFacilityRepository facilityRepository;
    private final Scanner scanner = new Scanner(System.in);

    public BookingService(IBookingRepository bookingRepository, ICustomerRepository customerRepository, IFacilityRepository facilityRepository) {
        this.bookingRepository = bookingRepository;
        this.customerRepository = customerRepository;
        this.facilityRepository = facilityRepository;
    }

    @Override
    public void addBooking() {
        // nhap booking id
        String bookingId;
        do {
            System.out.print("Enter booking id: ");
            bookingId = scanner.nextLine();
        } while (!ValidationUtils.validateNotEmpty(bookingId, "Booking id cannot be empty!"));

        // nhap ngay booking
        String bookingDate;
        do {
            System.out.print("Enter booking date (dd/MM/yyyy): ");
            bookingDate = scanner.nextLine();
        } while (ValidationUtils.validateDateFormat(bookingDate, "Invalid date format! Please try again"));

        // nhap ngay bat dau va ket thuc
        String startDate;
        String endDate;
        do {
            System.out.print("Enter start date (dd/MM/yyyy): ");
            startDate = scanner.nextLine();
        } while (ValidationUtils.validateDateFormat(startDate, "Invalid date format! Please try again"));

        do {
            System.out.print("Enter end date (dd/MM/yyyy): ");
            endDate = scanner.nextLine();
        } while (ValidationUtils.validateDateFormat(endDate, "Invalid date format! Please try again"));

        while (!ValidationUtils.validateStartBeforeEnd(startDate, endDate)) {
            System.out.println("Start date must be earlier than end date! Please enter again.");
            System.out.print("Enter start date (dd/MM/yyyy): ");
            startDate = scanner.nextLine();
            System.out.print("Enter end date (dd/MM/yyyy): ");
            endDate = scanner.nextLine();
        }

        // chon khach hang
        Customer selectedCustomer = null;
        List<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            System.out.println("No customers available! Cannot proceed.");
            return;
        }

        System.out.println("Available Customers:");
        for (int i = 0; i < customers.size(); i++) {
            Customer customer = customers.get(i);
            System.out.println((i + 1) + ". " + customer.getId() + " - " + customer.getName());
        }

        int selectedCustomerIndex;
        do {
            System.out.print("Select customer number (1-" + customers.size() + "): ");
            try {
                selectedCustomerIndex = Integer.parseInt(scanner.nextLine());
                if (selectedCustomerIndex >= 1 && selectedCustomerIndex <= customers.size()) {
                    selectedCustomer = customers.get(selectedCustomerIndex - 1);
                    break;
                } else {
                    System.out.println("Invalid selection! Please choose between 1 and " + customers.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);

        System.out.println("Customer selected successfully: " + selectedCustomer.getName());

        // chon facility
        System.out.println("Select service type: ");
        System.out.println("1. Villa");
        System.out.println("2. House");
        System.out.println("3. Room");

        int serviceType;
        do {
            try {
                System.out.print("Select service type: ");
                serviceType = Integer.parseInt(scanner.nextLine());
                if (serviceType < 1 || serviceType > 3) {
                    System.out.println("Invalid service type! Please select 1, 2, or 3.");
                } else {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (1, 2, or 3).");
            }
        } while (true);

        Facility selectedFacility = null;
        List<Facility> availableFacilities = new ArrayList<>();

        switch (serviceType) {
            case 1:
                System.out.println("Available Villas:");
                availableFacilities = facilityRepository.findAllByType("Villa");
                break;
            case 2:
                System.out.println("Available Houses:");
                availableFacilities = facilityRepository.findAllByType("House");
                break;
            case 3:
                System.out.println("Available Rooms:");
                availableFacilities = facilityRepository.findAllByType("Room");
                break;
        }

        if (availableFacilities.isEmpty()) {
            System.out.println("No available services for the selected type! Cannot proceed.");
            return;
        }

        for (int i = 0; i < availableFacilities.size(); i++) {
            Facility facility = availableFacilities.get(i);
            boolean isBooked = isFacilityBooked(facility.getId());
            System.out.println((i + 1) + ". " + facility.getId() + " - " + facility.getName() + (isBooked ? " (Already Booked)" : ""));
        }

        int selectedIndex;
        do {
            System.out.print("Select service number (1-" + availableFacilities.size() + "): ");
            try {
                selectedIndex = Integer.parseInt(scanner.nextLine());
                if (selectedIndex >= 1 && selectedIndex <= availableFacilities.size()) {
                    Facility tempFacility = availableFacilities.get(selectedIndex - 1);

                    if (isFacilityBooked(tempFacility.getId())) {
                        System.out.println("This service has already been booked! Please select another one.");
                    } else {
                        selectedFacility = tempFacility;
                        break;
                    }

                } else {
                    System.out.println("Invalid selection! Please choose between 1 and " + availableFacilities.size());
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number.");
            }
        } while (true);


        System.out.println("Service selected successfully: " + selectedFacility.getName());

        Booking booking = new Booking(bookingId, bookingDate, startDate, endDate, selectedCustomer.getId(), selectedFacility.getId());
        bookingRepository.add(booking);
        System.out.println("New booking added successfully!");

        facilityRepository.incrementUsageCount(selectedFacility.getId());
        if (facilityRepository.findFacilitiesNeedingMaintenance().containsKey(selectedFacility)) {
            System.out.println("Warning: This facility has reached usage limit and needs maintenance!");
        }
    }

    @Override
    public void displayBooking() {
        TreeSet<Booking> bookings = bookingRepository.findAll();
        if (bookings.isEmpty()) {
            System.out.println("Nothing");
        } else {
            for (Booking booking : bookings) {
                Customer customer = customerRepository.findById(booking.getCustomerId());
                Facility facility = facilityRepository.findById(booking.getFacilityId());

                System.out.println("---------------------------------------------------");
                System.out.println("Booking Details:");
                System.out.println("  Booking ID: " + booking.getBookingId());
                System.out.println("  Booking Date: " + booking.getBookingDate());
                System.out.println("  Start Date: " + booking.getStartDate());
                System.out.println("  End Date: " + booking.getEndDate());
                System.out.println();
                System.out.println("Customer Information:");
                System.out.println(customer != null ? customer.getDetails() : "  Customer not found");
                System.out.println();
                System.out.println("Service Information:");
                System.out.println(facility != null ? facility.getDetails() : "  Facility not found");
                System.out.println("---------------------------------------------------");
            }
        }
    }

    private boolean isFacilityBooked(String facilityId) {
        TreeSet<Booking> bookings = bookingRepository.findAll();
        for (Booking booking : bookings) {
            if (booking.getFacilityId().equals(facilityId)) {
                return true;
            }
        }
        return false;
    }

}

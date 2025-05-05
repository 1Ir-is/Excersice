package bai_tap_them.case_study_furuma.controllers;

import bai_tap_them.case_study_furuma.repositories.booking.BookingRepository;
import bai_tap_them.case_study_furuma.repositories.booking.IBookingRepository;
import bai_tap_them.case_study_furuma.repositories.contract.ContractRepository;
import bai_tap_them.case_study_furuma.repositories.contract.IContractRepository;
import bai_tap_them.case_study_furuma.repositories.customer.CustomerRepository;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;
import bai_tap_them.case_study_furuma.repositories.employee.EmployeeRepository;
import bai_tap_them.case_study_furuma.repositories.employee.IEmployeeRepository;
import bai_tap_them.case_study_furuma.repositories.facility.FacilityRepository;
import bai_tap_them.case_study_furuma.repositories.facility.IFacilityRepository;
import bai_tap_them.case_study_furuma.repositories.promotion.IPromotionRepository;
import bai_tap_them.case_study_furuma.repositories.promotion.PromotionRepository;
import bai_tap_them.case_study_furuma.services.booking.BookingService;
import bai_tap_them.case_study_furuma.services.booking.IBookingService;
import bai_tap_them.case_study_furuma.services.contract.ContractService;
import bai_tap_them.case_study_furuma.services.contract.IContractService;
import bai_tap_them.case_study_furuma.services.customer.CustomerService;
import bai_tap_them.case_study_furuma.services.customer.ICustomerService;
import bai_tap_them.case_study_furuma.services.employee.EmployeeService;
import bai_tap_them.case_study_furuma.services.employee.IEmployeeService;
import bai_tap_them.case_study_furuma.services.facility.FacilityService;
import bai_tap_them.case_study_furuma.services.facility.IFacilityService;
import bai_tap_them.case_study_furuma.services.promotion.IPromotionService;
import bai_tap_them.case_study_furuma.services.promotion.PromotionService;
import bai_tap_them.case_study_furuma.utils.MenuPrinter;
import bai_tap_them.case_study_furuma.view.CommonView;

import java.util.Scanner;

public class FuramaController {
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();
    private final IEmployeeService employeeService = new EmployeeService(employeeRepository);

    private final ICustomerRepository customerRepository = new CustomerRepository();
    private final ICustomerService customerService = new CustomerService(customerRepository);

    private final IFacilityRepository facilityRepository = new FacilityRepository();
    private final IFacilityService facilityService = new FacilityService(facilityRepository);

    private final IBookingRepository bookingRepository = new BookingRepository();
    private final IBookingService bookingService = new BookingService(bookingRepository, customerRepository, facilityRepository);

    private final IContractRepository contractRepository = new ContractRepository();
    private final IContractService contactService = new ContractService(bookingRepository, facilityRepository, contractRepository);

    private final IPromotionRepository promotionRepository = new PromotionRepository();
    private final IPromotionService promotionService = new PromotionService(promotionRepository);

    private final Scanner scanner = new Scanner(System.in);

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            MenuPrinter.printMainMenu();
            choice = CommonView.getChoice(6);

            switch (choice) {
                case 1:
                    displayEmployeeMenu();
                    break;
                case 2:
                    displayCustomerMenu();
                    break;
                case 3:
                    displayFacilityMenu();
                    break;
                case 4:
                    displayBookingMenu();
                    break;
                case 5:
                    displayPromotionMenu();
                    break;
                case 6:
                    if (confirmExit(scanner)) {
                        System.out.println("Exit the program!");
                    } else {
                        choice = -1;
                    }
                    break;
                default:
                    System.out.println("Invalid option. Please try again");
            }

        } while (choice != 6);
    }

    private void displayEmployeeMenu() {
        int choice;
        do {
            MenuPrinter.printEmployeeMenu();
            choice = CommonView.getChoice(4);

            switch (choice) {
                case 1:
                    employeeService.display();
                    CommonView.goBack(scanner);
                    break;
                case 2:
                    employeeService.add();
                    CommonView.goBack(scanner);
                    break;
                case 3:
                    employeeService.edit();
                    CommonView.goBack(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayCustomerMenu() {
        int choice;
        do {
            MenuPrinter.printCustomerMenu();
            choice = CommonView.getChoice(4);

            switch (choice) {
                case 1:
                    customerService.display();
                    CommonView.goBack(scanner);
                    break;
                case 2:
                    customerService.add();
                    CommonView.goBack(scanner);
                    break;
                case 3:
                    customerService.edit();
                    CommonView.goBack(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayFacilityMenu() {
        int choice;
        do {
            MenuPrinter.printFacilityMenu();
            choice = CommonView.getChoice(4);

            switch (choice) {
                case 1:
                    facilityService.display();
                    CommonView.goBack(scanner);
                    break;
                case 2:
                    facilityService.add();
                    CommonView.goBack(scanner);
                    break;
                case 3:
                    facilityService.displayFacilitiesNeedingMaintenance();
                    CommonView.goBack(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayBookingMenu() {
        int choice;
        do {
            MenuPrinter.printBookingMenu();

            choice = CommonView.getChoice(6);

            switch (choice) {
                case 1:
                    bookingService.addBooking();
                    CommonView.goBack(scanner);
                    break;
                case 2:
                    bookingService.displayBooking();
                    CommonView.goBack(scanner);
                    break;
                case 3:
                    contactService.add();
                    CommonView.goBack(scanner);
                    break;
                case 4:
                    contactService.display();
                    CommonView.goBack(scanner);
                    break;
                case 5:
                    contactService.edit();
                    CommonView.goBack(scanner);
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private void displayPromotionMenu() {
        int choice;
        do {
            MenuPrinter.printPromotionMenu();
            choice = CommonView.getChoice(3);

            switch (choice) {
                case 1:
                    promotionService.displayCustomersByYear();
                    CommonView.goBack(scanner);
                    break;
                case 2:
                    promotionService.distributeVouchers();
                    CommonView.goBack(scanner);
                    break;
                case 3:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private boolean confirmExit(Scanner scanner) {
        while (true) {
            System.out.print("Are you sure want to exit the program? (Y/N): ");
            String confirmation = scanner.nextLine().trim().toUpperCase();
            if (confirmation.equals("Y")) {
                return true;
            } else if (confirmation.equals("N")) {
                return false;
            } else {
                System.out.println("Invalid option. Please try again!");
            }
        }
    }
}

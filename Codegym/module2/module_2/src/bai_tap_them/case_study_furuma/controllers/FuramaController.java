package bai_tap_them.case_study_furuma.controllers;

import bai_tap_them.case_study_furuma.repositories.customer.CustomerRepository;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;
import bai_tap_them.case_study_furuma.repositories.employee.EmployeeRepository;
import bai_tap_them.case_study_furuma.repositories.employee.IEmployeeRepository;
import bai_tap_them.case_study_furuma.repositories.facility.FacilityRepository;
import bai_tap_them.case_study_furuma.repositories.facility.IFacilityRepository;
import bai_tap_them.case_study_furuma.services.customer.CustomerService;
import bai_tap_them.case_study_furuma.services.customer.ICustomerService;
import bai_tap_them.case_study_furuma.services.employee.EmployeeService;
import bai_tap_them.case_study_furuma.services.employee.IEmployeeService;
import bai_tap_them.case_study_furuma.services.facility.FacilityService;
import bai_tap_them.case_study_furuma.services.facility.IFacilityService;
import bai_tap_them.case_study_furuma.utils.MenuPrinter;
import bai_tap_them.case_study_furuma.utils.ValidationUtils;
import bai_tap_them.case_study_furuma.view.CommonView;

import java.util.Scanner;

public class FuramaController {
    private final IEmployeeRepository employeeRepository = new EmployeeRepository();
    private final IEmployeeService employeeService = new EmployeeService(employeeRepository);

    private final ICustomerRepository customerRepository = new CustomerRepository();
    private final ICustomerService customerService = new CustomerService(customerRepository);

    private final IFacilityRepository facilityRepository = new FacilityRepository();
    private final IFacilityService facilityService = new FacilityService(facilityRepository);

    public void displayMainMenu() {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            MenuPrinter.printMainMenu();
            choice = CommonView.getChoice(6);

            switch (choice) {
                case 1:
                    displayEmployeeMenu(scanner);
                    break;
                case 2:
                    displayCustomerMenu(scanner);
                    break;
                case 3:
                    displayFacilityMenu(scanner);
                    break;
                case 4:
                    displayBookingMenu(scanner);
                    break;
                case 5:
                    displayPromotionMenu(scanner);
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

    private void displayEmployeeMenu(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printEmployeeMenu();
            choice = CommonView.getChoice(4);

            switch (choice) {
                case 1:
                    employeeService.display();
                    goBack(scanner);
                    break;
                case 2:
                    employeeService.add();
                    goBack(scanner);
                    break;
                case 3:
                    employeeService.edit();
                    goBack(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayCustomerMenu(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printCustomerMenu();
            choice = CommonView.getChoice(4);

            switch (choice) {
                case 1:
                    customerService.display();
                    goBack(scanner);
                    break;
                case 2:
                    customerService.add();
                    goBack(scanner);
                    break;
                case 3:
                    customerService.edit();
                    goBack(scanner);
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayFacilityMenu(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printFacilityMenu();
            choice = CommonView.getChoice(4);

            switch (choice) {
                case 1:
                    facilityService.display();
                    break;
                case 2:
                    System.out.println("Add new facility");
                    break;
                case 3:
                    System.out.println("Display list facility maintenance");
                    break;
                case 4:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again!");
            }
        } while (true);
    }

    private void displayBookingMenu(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printBookingMenu();

            choice = CommonView.getChoice(6);

            switch (choice) {
                case 1:
                    System.out.println("Adding a new booking...");
                    break;
                case 2:
                    System.out.println("Displaying list of bookings...");
                    break;
                case 3:
                    System.out.println("Creating new contracts...");
                    break;
                case 4:
                    System.out.println("Displaying list of contracts...");
                    break;
                case 5:
                    System.out.println("Editing contracts...");
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (true);
    }

    private void displayPromotionMenu(Scanner scanner) {
        int choice;
        do {
            MenuPrinter.printPromotionMenu();
            choice = CommonView.getChoice(3);

            switch (choice) {
                case 1:
                    System.out.println("Displaying list of customers using services...");
                    break;
                case 2:
                    System.out.println("Displaying list of customers getting vouchers...");
                    break;
                case 3:
                    System.out.println("Returning to main menu...");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
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

    private void goBack(Scanner scanner) {
        System.out.println("Press Enter to return....");
        scanner.nextLine();
    }
}

package bai_tap_them.electric_bill.views;

import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.utils.SaveFileUtils;
import bai_tap_them.electric_bill.utils.ValidationUtils;

import java.util.List;
import java.util.Scanner;

public class VietnamCustomerView {
    private static final Scanner scanner = new Scanner(System.in);
    private static final String CUSTOMER_TYPE_FILE = "bai_tap_them/electric_bill/datas/customer_type.csv";

    public static VietnameseCustomer getVietNamCustomerInput() {
        System.out.print("Enter customer id: ");
        String id = ValidationUtils.validateInput("KHVN-\\d{5}", "Invalid id. Please try again!");
        System.out.print("Enter customer name: ");
        String name = bai_tap_them.case_study_furuma.utils.ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must capitalize the first letter of each word.");

        List<String> customerTypes = SaveFileUtils.readFromFile(CUSTOMER_TYPE_FILE);
        System.out.println("Available customer types:");
        for (int i = 0; i < customerTypes.size(); i++) {
            System.out.println((i + 1) + ". " + customerTypes.get(i));
        }

        String customerType = null;
        while (true) {
            System.out.print("Select a customer type by number: ");
            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
                if (choice >= 1 && choice <= customerTypes.size()) {
                    customerType = customerTypes.get(choice - 1).split(",")[0];
                    break;
                } else {
                    System.out.println("Invalid choice. Please select a valid number.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
            }
        }

        System.out.print("Enter consumption limit: ");
        int consumptionLimit = ValidationUtils.validatePositiveInt("Invalid input! Please try again.");
        return new VietnameseCustomer(id, name, customerType, consumptionLimit);
    }
}
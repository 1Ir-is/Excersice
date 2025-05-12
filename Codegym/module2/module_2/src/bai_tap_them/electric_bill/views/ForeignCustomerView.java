package bai_tap_them.electric_bill.views;

import bai_tap_them.electric_bill.models.ForeignCustomer;
import bai_tap_them.electric_bill.utils.ValidationUtils;

import java.util.Scanner;

public class ForeignCustomerView {
    private static final Scanner scanner = new Scanner(System.in);

    public static ForeignCustomer getForeignCustomerInput() {
        System.out.print("Enter customer id: ");
        String id = ValidationUtils.validateInput("KHNN-\\d{5}", "Invalid id. Please try again!");
        System.out.print("Enter customer name: ");
        String name = ValidationUtils.validateInput("[A-Z][a-z]*(\\s[A-Z][a-z]*)*", "Name must capitalize the first letter of each word.");
        System.out.print("Enter nationality: ");
        String nationality = scanner.nextLine();
        ValidationUtils.validateNotEmpty(nationality, "Invalid input! Please try again.");
        return new ForeignCustomer(id, name, nationality);
    }
}

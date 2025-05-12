package bai_tap_them.electric_bill.views;

import bai_tap_them.electric_bill.models.Bill;
import bai_tap_them.electric_bill.models.VietnameseCustomer;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BillView {
    private static final Scanner scanner = new Scanner(System.in);

    public static Bill getBillInput(List<VietnameseCustomer> customers) {
        System.out.println("Available customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }

        System.out.print("Select a customer by number: ");
        int customerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (customerIndex < 0 || customerIndex >= customers.size()) {
            System.out.println("Invalid customer selection.");
            return null;
        }

        VietnameseCustomer customer = customers.get(customerIndex);

        System.out.print("Enter bill date (yyyy-mm-dd): ");
        LocalDate billDate = LocalDate.parse(scanner.nextLine());

        System.out.print("Enter quantity (KW): ");
        int quantity = Integer.parseInt(scanner.nextLine());

        System.out.print("Enter unit price: ");
        double unitPrice = Double.parseDouble(scanner.nextLine());

        double totalAmount = calculateTotalAmount(customer, quantity, unitPrice);

        return new Bill(customer.getId(), billDate, quantity, unitPrice, totalAmount);
    }

    public static void displayBills(List<Bill> bills) {
        if (bills.isEmpty()) {
            System.out.println("No bills available.");
        } else {
            for (Bill bill : bills) {
                System.out.println(bill);
            }
        }
    }

    public static int getBillIdInput() {
        System.out.print("Enter bill ID: ");
        return Integer.parseInt(scanner.nextLine());
    }

    public static String getCustomerIdInput(List<VietnameseCustomer> customers) {
        System.out.println("Available customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i));
        }

        System.out.print("Select a customer by number: ");
        int customerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (customerIndex < 0 || customerIndex >= customers.size()) {
            System.out.println("Invalid customer selection.");
            return null;
        }

        return customers.get(customerIndex).getId();
    }

    public static void displayBillDetails(Bill bill, List<VietnameseCustomer> customers) {
        String customerName = customers.stream()
                .filter(c -> c.getId().equals(bill.getCustomerId()))
                .map(VietnameseCustomer::getName)
                .findFirst()
                .orElse("Unknown");

        System.out.println("Bill Details:");
        System.out.println("Bill ID: " + bill.getBillId());
        System.out.println("Customer Name: " + customerName);
        System.out.println("Bill Date: " + bill.getBillDate());
        System.out.println("Quantity: " + bill.getQuantity());
        System.out.println("Unit Price: " + bill.getUnitPrice());
        System.out.println("Total Amount: " + bill.getTotalAmount());
    }

    private static double calculateTotalAmount(VietnameseCustomer customer, int quantity, double unitPrice) {
        if (quantity <= customer.getConsumptionLimit()) {
            return quantity * unitPrice;
        } else {
            int excess = quantity - customer.getConsumptionLimit();
            return (customer.getConsumptionLimit() * unitPrice) + (excess * unitPrice * 2.5);
        }
    }
}
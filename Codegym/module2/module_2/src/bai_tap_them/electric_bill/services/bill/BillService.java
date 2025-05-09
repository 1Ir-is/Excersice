package bai_tap_them.electric_bill.services.bill;

import bai_tap_them.electric_bill.models.Bill;
import bai_tap_them.electric_bill.models.Customer;
import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.repositories.bill.IBillRepository;
import bai_tap_them.electric_bill.repositories.customers.ICustomerRepository;


import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class BillService implements IBillService {
    private final IBillRepository billRepository;
    private final ICustomerRepository customerRepository;

    public BillService(IBillRepository billRepository, ICustomerRepository customerRepository) {
        this.billRepository = billRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void addBill() {
        Scanner scanner = new Scanner(System.in);

        List<Customer> customers = customerRepository.findAll();
        if (customers.isEmpty()) {
            System.out.println("No customers available.");
            return;
        }
        System.out.println("Available Customers:");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println((i + 1) + ". " + customers.get(i).getName() + " (ID: " + customers.get(i).getId() + ")");
        }


        System.out.print("Select a customer by number: ");
        int customerIndex = Integer.parseInt(scanner.nextLine()) - 1;
        if (customerIndex < 0 || customerIndex >= customers.size()) {
            System.out.println("Invalid selection.");
            return;
        }
        Customer selectedCustomer = customers.get(customerIndex);

        System.out.print("Enter bill date (yyyy-mm-dd): ");
        LocalDate billDate = LocalDate.parse(scanner.nextLine());
        System.out.print("Enter quantity (KW): ");
        int quantity = Integer.parseInt(scanner.nextLine());
        System.out.print("Enter unit price: ");
        double unitPrice = Double.parseDouble(scanner.nextLine());

        double totalAmount;
        if (selectedCustomer instanceof VietnameseCustomer vnCustomer) {
            int limit = vnCustomer.getConsumptionLimit();
            if (quantity <= limit) {
                totalAmount = quantity * unitPrice;
            } else {
                totalAmount = (limit * unitPrice) + ((quantity - limit) * unitPrice * 2.5);
            }
        } else {
            totalAmount = quantity * unitPrice;
        }

        String billId = "B" + (billRepository.findAll().size() + 1);
        Bill bill = new Bill(billId, selectedCustomer.getId(), billDate, quantity, unitPrice, totalAmount);
        billRepository.add(bill);
        System.out.println("Bill added successfully!");
    }

    @Override
    public void editBill() {

    }

    @Override
    public void displayBill() {
        List<Bill> bills = billRepository.findAll();
        if (bills.isEmpty()) {
            System.out.println("No bills available.");
            return;
        }
        for (Bill bill : bills) {
            Customer customer = customerRepository.findById(bill.getCustomerId());
            String customerName = (customer != null) ? customer.getName() : "Unknown";
            System.out.println("Bill ID: " + bill.getBillId());
            System.out.println("Customer: " + customerName);
            System.out.println("Date: " + bill.getBillDate());
            System.out.println("Quantity: " + bill.getQuantity() + " KW");
            System.out.println("Unit Price: " + bill.getUnitPrice());
            System.out.println("Total Amount: " + bill.getTotalAmount());
            System.out.println("-------------------------");
        }
    }
}
package bai_tap_them.electric_bill.controllers;

import bai_tap_them.electric_bill.models.Bill;
import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.services.bill.IBillService;
import bai_tap_them.electric_bill.repositories.customers.VietnamCustomerRepository;
import bai_tap_them.electric_bill.views.BillView;

import java.util.List;

public class BillController {
    private final IBillService billService;
    private final VietnamCustomerRepository customerRepository;

    public BillController(IBillService billService, VietnamCustomerRepository customerRepository) {
        this.billService = billService;
        this.customerRepository = customerRepository;
    }

    public void addBill() {
        List<VietnameseCustomer> customers = customerRepository.findAll();
        Bill bill = BillView.getBillInput(customers);
        if (bill != null) {
            billService.addBill(bill);
            System.out.println("Bill added successfully!");
        } else {
            System.out.println("Failed to add bill.");
        }
    }

    public void editBill() {
        List<Bill> bills = billService.getAllBills();
        BillView.displayBills(bills);

        int billId = BillView.getBillIdInput();
        Bill bill = billService.findBillById(billId);

        if (bill == null) {
            System.out.println("Bill not found.");
            return;
        }

        String newCustomerId = BillView.getCustomerIdInput(customerRepository.findAll());
        if (newCustomerId != null) {
            bill.setCustomerId(newCustomerId);
            billService.editBill(bill);
            System.out.println("Bill updated successfully!");
        } else {
            System.out.println("Failed to update bill.");
        }
    }

    public void displayBillDetails() {
        List<Bill> bills = billService.getAllBills();
        BillView.displayBills(bills);

        int billId = BillView.getBillIdInput();
        Bill bill = billService.findBillById(billId);

        if (bill != null) {
            BillView.displayBillDetails(bill, customerRepository.findAll());
        } else {
            System.out.println("Bill not found.");
        }
    }
}
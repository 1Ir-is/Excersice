package bai_tap_them.electric_bill.controllers;

import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.services.customers.VietnamCustomerService;
import bai_tap_them.electric_bill.views.VietnamCustomerView;

import java.util.List;

public class VietnamCustomerController {
    private final VietnamCustomerService service = new VietnamCustomerService();

    public void addCustomer() {
        VietnameseCustomer customer = VietnamCustomerView.getVietNamCustomerInput();
        service.add(customer);
        System.out.println("Vietnamese customer added successfully!");
    }

    public void displayCustomers() {
        List<VietnameseCustomer> customers = service.getAll();
        for (VietnameseCustomer customer : customers) {
            System.out.println(customer);
        }
    }

    public List<VietnameseCustomer> searchCustomer(String keyword) {
        return service.searchByName(keyword);
    }
}
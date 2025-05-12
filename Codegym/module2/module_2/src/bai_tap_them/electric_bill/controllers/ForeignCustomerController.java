package bai_tap_them.electric_bill.controllers;

import bai_tap_them.electric_bill.models.ForeignCustomer;
import bai_tap_them.electric_bill.services.customers.ForeignCustomerService;
import bai_tap_them.electric_bill.views.ForeignCustomerView;

import java.util.List;

public class ForeignCustomerController {
    private final ForeignCustomerService service = new ForeignCustomerService();

    public void addCustomer() {
        ForeignCustomer customer = ForeignCustomerView.getForeignCustomerInput();
        service.add(customer);
        System.out.println("Foreign customer added successfully!");
    }

    public void displayCustomers() {
        List<ForeignCustomer> customers = service.getAll();
        for (ForeignCustomer customer : customers) {
            System.out.println(customer);
        }
    }

    public List<ForeignCustomer> searchCustomer(String keyword) {
        return service.searchByName(keyword);
    }
}
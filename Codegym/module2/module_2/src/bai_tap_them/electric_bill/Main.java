package bai_tap_them.electric_bill;

import bai_tap_them.electric_bill.controllers.BillController;
import bai_tap_them.electric_bill.controllers.MainController;
import bai_tap_them.electric_bill.repositories.bill.BillRepository;
import bai_tap_them.electric_bill.repositories.customers.VietnamCustomerRepository;
import bai_tap_them.electric_bill.services.bill.BillService;

public class Main {
    public static void main(String[] args) {

        VietnamCustomerRepository customerRepository = new VietnamCustomerRepository();
        BillRepository billRepository = new BillRepository();

        BillService billService = new BillService(billRepository);
        BillController billController = new BillController(billService, customerRepository);
        MainController mainController = new MainController(billController);

        mainController.displayMainMenu();
    }
}
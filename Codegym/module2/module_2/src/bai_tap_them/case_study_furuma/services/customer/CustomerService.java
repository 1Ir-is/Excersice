package bai_tap_them.case_study_furuma.services.customer;


import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.repositories.customer.ICustomerRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class CustomerService implements ICustomerService {
    private final ICustomerRepository customerRepository;
    private final Scanner scanner = new Scanner(System.in);

    public CustomerService(ICustomerRepository repository) {
        this.customerRepository = repository;
    }

    @Override
    public void display() {
        ArrayList<Customer> customers = customerRepository.findAll();

        if (customers.isEmpty()) {
            System.out.println("No customer found!");
            return;
        }

        String line = "+--------+----------------------+------------+--------+--------------+--------------+----------------------+------------------+------------------+---------------+";
        String headerFormat = "| %-6s | %-20s | %-10s | %-6s | %-12s | %-12s | %-20s | %-16s | %-16s |%n";

        System.out.println(line);
        System.out.printf(headerFormat, "ID", "Name", "DOB", "Gender", "ID Card", "Phone", "Email", "Customer Type", "Address");
        System.out.println(line);

        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getDetails());
        }

        System.out.println(line);
        System.out.println();
    }

    @Override
    public void add() {

    }

    @Override
    public void edit() {

    }
}

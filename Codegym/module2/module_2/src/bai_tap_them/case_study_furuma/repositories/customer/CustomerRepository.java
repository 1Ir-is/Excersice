package bai_tap_them.case_study_furuma.repositories.customer;

import bai_tap_them.case_study_furuma.models.Customer;

import java.util.ArrayList;

public class CustomerRepository implements ICustomerRepository {
    private static final ArrayList<Customer> customers = new ArrayList<>();

    static {
        customers.add(new Customer("KH-0001", "Le Van Tam", "01/01/2004", "Male", "123456789", "0914234903", "tam@example.com", "Diamond", "Viet Nam"));
        customers.add(new Customer("KH-0002", "Ton That Duy", "01/01/2004", "Male", "234567890", "09123832834", "duy@example.com", "Gold", "Viet Nam"));
        customers.add(new Customer("KH-0003", "Nguyen Thanh Nhon", "01/01/2004", "Male", "345678901", "02934092343", "nhon@example.com", "Silver", "Viet Nam"));
    }

    @Override
    public ArrayList<Customer> findAll() {
        return customers;
    }
}

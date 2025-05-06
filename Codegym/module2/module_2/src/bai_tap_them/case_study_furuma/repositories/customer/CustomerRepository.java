package bai_tap_them.case_study_furuma.repositories.customer;

import bai_tap_them.case_study_furuma.models.Customer;
import bai_tap_them.case_study_furuma.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private static final String CUSTOMER_FILE = "bai_tap_them/case_study_furuma/datas/customers.csv";


    @Override
    public Customer findById(String id) {
        for (Customer customer : findAll()) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }

    @Override
    public void saveAll(List<Customer> customers) {
        List<String> dataLines = new ArrayList<>();
        for (Customer customer : customers) {
            dataLines.add(customer.toCSV());
        }
        SaveFileUtils.writeToFile(CUSTOMER_FILE, dataLines, false);
    }

    @Override
    public void add(Customer customer) {
        List<String> dataLines = new ArrayList<>();
        dataLines.add(customer.toCSV());
        SaveFileUtils.writeToFile(CUSTOMER_FILE, dataLines, true);
    }

    @Override
    public ArrayList<Customer> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(CUSTOMER_FILE);
        ArrayList<Customer> customers = new ArrayList<>();
        for (String line : lines){
            customers.add(Customer.fromCSV(line));
        }
        return customers;
    }
}

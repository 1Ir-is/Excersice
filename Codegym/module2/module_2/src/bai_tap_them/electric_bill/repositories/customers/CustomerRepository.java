package bai_tap_them.electric_bill.repositories.customers;

import bai_tap_them.electric_bill.models.Customer;
import bai_tap_them.electric_bill.models.ForeignCustomer;
import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class CustomerRepository implements ICustomerRepository {
    private static final String CUSTOMER_FILE = "bai_tap_them/electric_bill/datas/customers.csv";
    private List<Customer> customers = new ArrayList<>();

    public CustomerRepository() {
        loadCustomersFromFile();
    }

    private void loadCustomersFromFile() {
        List<String> lines = SaveFileUtils.readFromFile(CUSTOMER_FILE);
        for (String line : lines) {
            String[] parts = line.split(",");
            if (parts[0].equals("VN")) {
                customers.add(new VietnameseCustomer(parts[1], parts[2], parts[3], Integer.parseInt(parts[4])));
            } else if (parts[0].equals("FOREIGN")) {
                customers.add(new ForeignCustomer(parts[1], parts[2], parts[3]));
            }
        }
    }

    @Override
    public void add(Customer customer) {
        customers.add(customer);
        List<String> customerData = new ArrayList<>();
        for (Customer c : customers) {
            customerData.add(c.toCSV());
        }
        SaveFileUtils.writeToFile(CUSTOMER_FILE, customerData, false);
    }

    @Override
    public List<Customer> findAll() {
        return customers;
    }

    @Override
    public Customer findById(String id) {
        for (Customer customer : customers) {
            if (customer.getId().equals(id)) {
                return customer;
            }
        }
        return null;
    }
}
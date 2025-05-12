package bai_tap_them.electric_bill.repositories.customers;

import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class VietnamCustomerRepository implements ICustomerRepository<VietnameseCustomer>{
    private static final String CUSTOMER_FILE = "bai_tap_them/electric_bill/datas/customers.csv";

    @Override
    public List<VietnameseCustomer> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(CUSTOMER_FILE);
        List<VietnameseCustomer> vietnameseCustomers = new ArrayList<>();
        for (String line : lines) {
           String[] parts = line.split("," , 2);
           if (parts.length > 1 && "VIETNAM".equals(parts[0])) {
               vietnameseCustomers.add(VietnameseCustomer.fromCSV(parts[1]));
           }
        }
        return vietnameseCustomers;
    }

    @Override
    public void save(List<VietnameseCustomer> data) {
        List<String> result = new ArrayList<>();
        List<String> lines = SaveFileUtils.readFromFile(CUSTOMER_FILE);
        for (String line : lines) {
            if (!line.startsWith("VIETNAM,")) {
                result.add(line);
            }
        }
        for (VietnameseCustomer vietnameseCustomer : data) {
            result.add("VIETNAM," + vietnameseCustomer.toCSV());
        }
        SaveFileUtils.writeToFile(CUSTOMER_FILE, result, false);
    }

    @Override
    public void add(VietnameseCustomer customer) {
        List<VietnameseCustomer> vietnameseCustomers = findAll();
        vietnameseCustomers.add(customer);
        save(vietnameseCustomers);
    }

    @Override
    public List<VietnameseCustomer> searchByName(String keyword) {
        List<VietnameseCustomer> result = new ArrayList<>();
        List<VietnameseCustomer> current = findAll();
        for (VietnameseCustomer vietnameseCustomer : current) {
            if (vietnameseCustomer.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(vietnameseCustomer);
            }
        }
        return result;
    }
}

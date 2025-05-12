package bai_tap_them.electric_bill.repositories.customers;

import bai_tap_them.electric_bill.models.ForeignCustomer;
import bai_tap_them.electric_bill.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class ForeignCustomerRepository implements ICustomerRepository<ForeignCustomer>{
    private static final String CUSTOMER_FILE = "bai_tap_them/electric_bill/datas/customers.csv";

    @Override
    public List<ForeignCustomer> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(CUSTOMER_FILE);
        List<ForeignCustomer> foreignCustomers = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split("," , 2);
            if (parts.length > 1 && "FOREIGN".equals(parts[0])) {
                foreignCustomers.add(ForeignCustomer.fromCSV(parts[1]));
            }
        }
        return foreignCustomers;
    }

    @Override
    public void save(List<ForeignCustomer> data) {
        List<String> result = new ArrayList<>();
        List<String> lines = SaveFileUtils.readFromFile(CUSTOMER_FILE);
        for (String line : lines) {
            if (!line.startsWith("FOREIGN,")) {
                result.add(line);
            }
        }
        for (ForeignCustomer foreignCustomer : data) {
            result.add("FOREIGN," + foreignCustomer.toCSV());
        }
        SaveFileUtils.writeToFile(CUSTOMER_FILE, result, false);
    }

    @Override
    public void add(ForeignCustomer customer) {
        List<ForeignCustomer> current = findAll();
        current.add(customer);
        save(current);
    }

    @Override
    public List<ForeignCustomer> searchByName(String keyword) {
        List<ForeignCustomer> result = new ArrayList<>();
        List<ForeignCustomer> current = findAll();
        for (ForeignCustomer foreignCustomer : current) {
            if (foreignCustomer.getName().toLowerCase().contains(keyword.toLowerCase())) {
                result.add(foreignCustomer);
            }
        }
        return result;
    }
}

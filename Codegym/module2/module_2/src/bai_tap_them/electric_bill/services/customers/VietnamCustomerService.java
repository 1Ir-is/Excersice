package bai_tap_them.electric_bill.services.customers;

import bai_tap_them.electric_bill.models.VietnameseCustomer;
import bai_tap_them.electric_bill.repositories.customers.VietnamCustomerRepository;

import java.util.List;

public class VietnamCustomerService implements ICustomerService<VietnameseCustomer> {
    private final VietnamCustomerRepository repository = new VietnamCustomerRepository();

    @Override
    public List<VietnameseCustomer> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(VietnameseCustomer customer) {
        repository.add(customer);
    }

    @Override
    public List<VietnameseCustomer> searchByName(String keyword) {
        return repository.searchByName(keyword);
    }
}

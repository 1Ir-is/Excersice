package bai_tap_them.electric_bill.services.customers;

import bai_tap_them.electric_bill.models.ForeignCustomer;
import bai_tap_them.electric_bill.repositories.customers.ForeignCustomerRepository;

import java.util.List;

public class ForeignCustomerService implements ICustomerService<ForeignCustomer> {
    private final ForeignCustomerRepository repository = new ForeignCustomerRepository();
    @Override
    public List<ForeignCustomer> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(ForeignCustomer customer) {
        repository.add(customer);
    }

    @Override
    public List<ForeignCustomer> searchByName(String keyword) {
        return repository.searchByName(keyword);
    }
}

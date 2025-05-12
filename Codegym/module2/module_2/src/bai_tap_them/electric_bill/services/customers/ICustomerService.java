package bai_tap_them.electric_bill.services.customers;

import java.util.List;

public interface ICustomerService<T> {
    List<T> getAll();
    void add(T customer);
    List<T> searchByName(String keyword);
}

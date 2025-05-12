package bai_tap_them.electric_bill.repositories.customers;

import java.util.List;

public interface ICustomerRepository<T> {
    List<T> findAll();
    void save(List<T> data);
    void add(T customer);
    List<T> searchByName(String keyword);
}

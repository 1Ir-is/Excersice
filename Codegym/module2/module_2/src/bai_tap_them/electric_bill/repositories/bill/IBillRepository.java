package bai_tap_them.electric_bill.repositories;

import bai_tap_them.electric_bill.models.Bill;

import java.util.List;

public interface IBillRepository {
    List<Bill> findAll();
    void save(List<Bill> bills);
    void add(Bill bill);
    void update(Bill bill);
    Bill findById(int id);
}
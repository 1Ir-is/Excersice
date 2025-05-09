package bai_tap_them.electric_bill.repositories.bill;

import bai_tap_them.electric_bill.models.Bill;

import java.util.List;

public interface IBillRepository {
    void add(Bill bill);
    List<Bill> findAll();
    Bill findById(String id);
    void update(Bill bill);
}

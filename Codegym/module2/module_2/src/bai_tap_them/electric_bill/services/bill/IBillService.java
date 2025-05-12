package bai_tap_them.electric_bill.services.bill;

import bai_tap_them.electric_bill.models.Bill;

import java.util.List;

public interface IBillService {
    void addBill(Bill bill);
    void editBill(Bill bill);
    Bill findBillById(int id);
    List<Bill> getAllBills();
}
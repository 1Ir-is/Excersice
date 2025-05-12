package bai_tap_them.electric_bill.services.bill;

import bai_tap_them.electric_bill.models.Bill;
import bai_tap_them.electric_bill.repositories.IBillRepository;

import java.util.List;

public class BillService implements IBillService {
    private final IBillRepository billRepository;

    public BillService(IBillRepository billRepository) {
        this.billRepository = billRepository;
    }

    @Override
    public void addBill(Bill bill) {
        billRepository.add(bill);
    }

    @Override
    public void editBill(Bill bill) {
        billRepository.update(bill);
    }

    @Override
    public Bill findBillById(int id) {
        return billRepository.findById(id);
    }

    @Override
    public List<Bill> getAllBills() {
        return billRepository.findAll();
    }
}
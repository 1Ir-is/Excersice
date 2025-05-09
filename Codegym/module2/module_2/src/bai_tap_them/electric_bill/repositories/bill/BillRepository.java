package bai_tap_them.electric_bill.repositories.bill;


import bai_tap_them.electric_bill.models.Bill;
import bai_tap_them.electric_bill.utils.SaveFileUtils;

import java.util.ArrayList;
import java.util.List;

public class BillRepository implements IBillRepository {
    private static final String BILL_FILE = "bai_tap_them/electric_bill/datas/bills.csv";
    private List<Bill> bills = new ArrayList<>();

    @Override
    public void add(Bill bill) {
        bills.add(bill);
        List<String> billData = new ArrayList<>();
        for (Bill b : bills) {
            billData.add(b.toCSV());
        }
        SaveFileUtils.writeToFile(BILL_FILE, billData, false);
    }

    @Override
    public List<Bill> findAll() {
        return bills;
    }

    @Override
    public Bill findById(String id) {
        for (Bill bill : bills) {
            if (bill.getBillId().equals(id)) {
                return bill;
            }
        }
        return null;
    }

    @Override
    public void update(Bill bill) {
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getBillId().equals(bill.getBillId())) {
                bills.set(i, bill);
                break;
            }
        }
        List<String> billData = new ArrayList<>();
        for (Bill b : bills) {
            billData.add(b.toCSV());
        }
        SaveFileUtils.writeToFile(BILL_FILE, billData, false);
    }
}
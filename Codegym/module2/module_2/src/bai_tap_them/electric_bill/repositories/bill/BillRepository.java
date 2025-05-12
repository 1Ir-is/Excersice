package bai_tap_them.electric_bill.repositories.bill;

import bai_tap_them.electric_bill.models.Bill;
import bai_tap_them.electric_bill.utils.SaveFileUtils;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class BillRepository implements bai_tap_them.electric_bill.repositories.IBillRepository {
    private static final String BILL_FILE = "bai_tap_them/electric_bill/datas/bills.csv";

    @Override
    public List<Bill> findAll() {
        List<String> lines = SaveFileUtils.readFromFile(BILL_FILE);
        List<Bill> bills = new ArrayList<>();
        for (String line : lines) {
            String[] parts = line.split(",");
            Bill bill = new Bill(
                    parts[1],
                    LocalDate.parse(parts[2]),
                    Integer.parseInt(parts[3]),
                    Double.parseDouble(parts[4]),
                    Double.parseDouble(parts[5])
            );
            bills.add(bill);
        }
        return bills;
    }

    @Override
    public void save(List<Bill> bills) {
        List<String> lines = new ArrayList<>();
        for (Bill bill : bills) {
            lines.add(bill.getBillId() + "," + bill.getCustomerId() + "," + bill.getBillDate() + "," +
                    bill.getQuantity() + "," + bill.getUnitPrice() + "," + bill.getTotalAmount());
        }
        SaveFileUtils.writeToFile(BILL_FILE, lines, false);
    }

    @Override
    public void add(Bill bill) {
        List<Bill> bills = findAll();
        bills.add(bill);
        save(bills);
    }

    @Override
    public void update(Bill bill) {
        List<Bill> bills = findAll();
        for (int i = 0; i < bills.size(); i++) {
            if (bills.get(i).getBillId() == bill.getBillId()) {
                bills.set(i, bill);
                break;
            }
        }
        save(bills);
    }

    @Override
    public Bill findById(int id) {
        return findAll().stream().filter(bill -> bill.getBillId() == id).findFirst().orElse(null);
    }
}
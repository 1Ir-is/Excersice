package bai_tap_them.electric_bill.models;

import java.time.LocalDate;

public class Bill {
    private String billId;
    private String customerId;
    private LocalDate billDate;
    private int quantity;
    private double unitPrice;
    private double totalAmount;

    public Bill(String billId, String customerId, LocalDate billDate, int quantity, double unitPrice, double totalAmount) {
        this.billId = billId;
        this.customerId = customerId;
        this.billDate = billDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
    }

    public String getBillId() {
        return billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public String toCSV() {
        return billId + "," + customerId + "," + billDate + "," + quantity + "," + unitPrice + "," + totalAmount;
    }
}
package bai_tap_them.electric_bill.models;

import java.time.LocalDate;

public class Bill {
    private static int idCounter = 1;
    private int billId;
    private String customerId;
    private LocalDate billDate;
    private int quantity;
    private double unitPrice;
    private double totalAmount;

    public Bill(String customerId, LocalDate billDate, int quantity, double unitPrice, double totalAmount) {
        this.billId = idCounter++;
        this.customerId = customerId;
        this.billDate = billDate;
        this.quantity = quantity;
        this.unitPrice = unitPrice;
        this.totalAmount = totalAmount;
    }

    public int getBillId() {
        return billId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public LocalDate getBillDate() {
        return billDate;
    }

    public void setBillDate(LocalDate billDate) {
        this.billDate = billDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "Bill ID: " + billId +
                ", Customer ID: " + customerId +
                ", Bill Date: " + billDate +
                ", Quantity: " + quantity +
                ", Unit Price: " + unitPrice +
                ", Total Amount: " + totalAmount;
    }
}
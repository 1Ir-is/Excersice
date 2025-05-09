package bai_tap_them.electric_bill.models;

public class VietnameseCustomer extends Customer {
    private String customerType;
    private int consumptionLimit;

    public VietnameseCustomer(String id, String name, String customerType, int consumptionLimit) {
        super(id, name);
        this.customerType = customerType;
        this.consumptionLimit = consumptionLimit;
    }

    public String getCustomerType() {
        return customerType;
    }

    public int getConsumptionLimit() {
        return consumptionLimit;
    }

    @Override
    public String toCSV() {
        return "VN," + getId() + "," + getName() + "," + customerType + "," + consumptionLimit;
    }
}
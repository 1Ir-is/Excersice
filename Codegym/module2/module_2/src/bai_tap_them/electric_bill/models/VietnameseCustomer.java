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

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public int getConsumptionLimit() {
        return consumptionLimit;
    }

    public void setConsumptionLimit(int consumptionLimit) {
        this.consumptionLimit = consumptionLimit;
    }

    @Override
    public String toCSV() {
        return id + "," + name + "," + customerType + "," + consumptionLimit + ",";
    }

    @Override
    public String getDetails() {
        return "VIETNAM: " + id + ", Name: " + name + ", Customer Type: " + customerType + ", Consumption Limit: " + consumptionLimit;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public static VietnameseCustomer fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new VietnameseCustomer(
                parts[0],
                parts[1],
                parts[2],
                Integer.parseInt(parts[3])
        );
    }
}
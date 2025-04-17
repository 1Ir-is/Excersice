package bai_tap_them.case_study_furuma.models;

public class Customer extends Person {

    private String customerType;
    private String address;

    public Customer(String id, String name, String dateOfBirth, String gender, String idCard, String phoneNuber, String email,
                    String customerType, String address) {
        super(id, name, dateOfBirth, gender, idCard, phoneNuber, email);
        this.customerType = customerType;
        this.address = address;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getDetails() {
        return "Customer: " + getName() + ", Customer Type: " + customerType + ", Address: " + address;
    }
}

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
        return String.format(
                "| %-6s | %-20s | %-10s | %-6s | %-12s | %-12s | %-20s | %-16s | %-16s |",
                getId(),
                getName(),
                getDateOfBirth(),
                getGender(),
                getIdCard(),
                getPhoneNumber(),
                getEmail(),
                customerType,
                address
        );
    }
}

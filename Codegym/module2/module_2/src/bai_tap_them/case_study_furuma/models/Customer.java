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
        return String.join("\n",
                "ID: " + getId(),
                "Name: " + getName(),
                "Date of Birth: " + getDateOfBirth(),
                "Gender: " + getGender(),
                "ID Card: " + getIdCard(),
                "Phone: " + getPhoneNumber(),
                "Email: " + getEmail(),
                "Customer Type: " + customerType,
                "Address: " + address
        );
    }

    public String toCSV() {
        return String.join(",",
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

    public static Customer fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Customer(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6],
                parts[7],
                parts[8]
        );
    }
}

package bai_tap_them.electric_bill.models;

public class ForeignCustomer extends Customer {
    private String nationality;

    public ForeignCustomer(String id, String name, String nationality) {
        super(id, name);
        this.nationality = nationality;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    @Override
    public String toCSV() {
        return id + "," + name + "," + nationality;
    }

    @Override
    public String getDetails() {
        return "FOREIGN: " + id + ", Name: " + name + ", Nationality: " + nationality;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public static ForeignCustomer fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new ForeignCustomer(
                parts[0],
                parts[1],
                parts[2]
        );
    }
}
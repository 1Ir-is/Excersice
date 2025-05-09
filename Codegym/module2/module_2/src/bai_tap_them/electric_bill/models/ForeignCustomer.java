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

    @Override
    public String toCSV() {
        return "FOREIGN," + getId() + "," + getName() + "," + nationality;
    }
}
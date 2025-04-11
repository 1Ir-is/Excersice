package ss5_access_modifier.bai_tap.product_management;

import java.text.DecimalFormat;

public class Product {
    private int id;
    private String name;
    private double price;

    public Product(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String toString() {
        DecimalFormat df = new DecimalFormat("#,###");
        return "List{" +
                "id= " + id +
                ", name= '" + name + '\'' +
                ", price= " + df.format(price) + " VNĐ" +
                '}';
    }
}

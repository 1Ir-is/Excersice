package ss5_access_modifier.bai_tap.product_management;

import java.text.DecimalFormat;

public class Product {
    private static int autoId = 1;
    private int id;
    private String name;
    private double price;

    public Product(String name, double price) {
        this.id = autoId++;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
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
        return "------------------------------\n" +
                "ID      : " + id + "\n" +
                "Name    : " + name + "\n" +
                "Price   : " + df.format(price) + " VNĐ\n" +
                "------------------------------";
    }
}

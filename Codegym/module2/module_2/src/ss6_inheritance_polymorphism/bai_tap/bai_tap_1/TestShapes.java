package ss6_inheritance_polymorphism.bai_tap.bai_tap_1;

public class TestShapes {
    public static void main(String[] args) {
        // Test Circle
        Circle circle = new Circle(5.0, "blue");
        System.out.println(circle);

        // Test Cylinder
        Cylinder cylinder = new Cylinder(5.0, "green", 10.0);
        System.out.println(cylinder);
    }
}
package ss7_abstract_interface.bai_tap.bai_tap_2;

import java.util.Random;

public class CircleTest {
    public static void main(String[] args) {
        Circle circle = new Circle(5.0);
        System.out.println("Before resizing: " + circle);

        Random random = new Random();
        double percent = 1 + random.nextInt(100);
        System.out.println("Resizing by " + percent + "%...");
        circle.resize(percent);

        System.out.println("After resizing: " + circle);
    }
}
package ss7_abstract_interface.bai_tap.bai_tap_1;

import java.util.Random;

public class RectangleTest {
    public static void main(String[] args) {
        Rectangle rectangle = new Rectangle(4.0, 6.0);
        System.out.println("Before resizing: " + rectangle);

        Random random = new Random();
        double percent = 1 + random.nextInt(100);
        System.out.println("Resizing by " + percent + "%...");
        rectangle.resize(percent);

        System.out.println("After resizing: " + rectangle);
    }
}
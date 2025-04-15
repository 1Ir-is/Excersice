package ss7_abstract_interface.bai_tap.bai_tap_2;

import java.util.Random;

public class ResizeableTest {
    public static void main(String[] args) {
        IResizeable[] shapes = new IResizeable[3];
        shapes[0] = new Circle(5.0);
        shapes[1] = new Rectangle(4.0, 6.0);
        shapes[2] = new Square(4.0);

        Random random = new Random();


        for (IResizeable shape : shapes) {
            System.out.println("Before resizing: " + shape);

            double percent = 1 + random.nextInt(100);
            System.out.println("Resizing by " + percent + "%...");
            shape.resize(percent);

            System.out.println("After resizing: " + shape);
            System.out.println();
        }
    }
}
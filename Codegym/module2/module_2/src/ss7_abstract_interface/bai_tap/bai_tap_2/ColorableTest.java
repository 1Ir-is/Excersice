package ss7_abstract_interface.bai_tap.bai_tap_2;

public class ColorableTest {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[1];
        shapes[0] = new Square(5.0);
        for (Shape shape : shapes) {
            System.out.println("Shape details: " + shape);

            if (shape instanceof IColorable) {
                ((IColorable) shape).howToColor();
            }

            System.out.println();
        }
    }
}
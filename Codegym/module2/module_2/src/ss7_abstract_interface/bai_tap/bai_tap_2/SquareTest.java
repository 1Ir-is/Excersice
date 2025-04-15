package ss7_abstract_interface.bai_tap.bai_tap_2;


import java.util.Random;

public class SquareTest {
    public static void main(String[] args) {
        Square square = new Square(4.0);
        System.out.println("Before resizing: " + square);
        Random random = new Random();
        double percent = 1 + random.nextInt(100);
        System.out.println("Resizing by " + percent + "%...");
        square.resize(percent);

        System.out.println("After resizing: " + square);
    }
}
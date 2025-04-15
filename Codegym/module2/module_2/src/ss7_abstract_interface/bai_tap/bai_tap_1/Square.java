package ss7_abstract_interface.bai_tap.bai_tap_1;

import ss7_abstract_interface.bai_tap.bai_tap_1.Resizeable;

public class Square implements Resizeable {
    private double side;

    public Square(double side) {
        this.side = side;
    }

    public double getSide() {
        return side;
    }

    public double getArea() {
        return side * side;
    }

    @Override
    public void resize(double percent) {
        side += side * percent / 100;
    }

    @Override
    public String toString() {
        return "Square with side=" + side + ", area=" + getArea();
    }
}
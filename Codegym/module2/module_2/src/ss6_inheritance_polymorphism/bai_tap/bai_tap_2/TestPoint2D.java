package ss6_inheritance_polymorphism.bai_tap.bai_tap_2;

public class TestPoint2D {
    public static void main(String[] args) {
        Point2D p1 = new Point2D();
        System.out.println("Point2D mặc định: " + p1);

        Point2D p2 = new Point2D(3.5f, 7.2f);
        System.out.println("Point2D khởi tạo: " + p2);

        p2.setX(1.1f);
        p2.setY(2.2f);
        System.out.println("Sau khi setX và setY: " + p2);

        p2.setXY(5.5f, 6.6f);
        System.out.println("Sau khi setXY: " + p2);

        float[] xy = p2.getXY();
        System.out.println("Giá trị getXY: [" + xy[0] + ", " + xy[1] + "]");
    }
}

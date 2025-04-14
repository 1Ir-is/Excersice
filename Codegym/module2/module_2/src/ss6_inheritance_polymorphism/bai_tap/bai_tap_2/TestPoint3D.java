package ss6_inheritance_polymorphism.bai_tap.bai_tap_2;

public class TestPoint3D {
    public static void main(String[] args) {
        Point3D p3 = new Point3D();
        System.out.println("Point3D mặc định: " + p3);

        Point3D p4 = new Point3D(1.1f, 2.2f, 3.3f);
        System.out.println("Point3D khởi tạo: " + p4);

        p4.setZ(4.4f);
        System.out.println("Sau khi setZ: " + p4);

        p4.setXYZ(7.7f, 8.8f, 9.9f);
        System.out.println("Sau khi setXYZ: " + p4);

        float[] xyz = p4.getXYZ();
        System.out.println("Giá trị getXYZ: [" + xyz[0] + ", " + xyz[1] + ", " + xyz[2] + "]");
    }
}

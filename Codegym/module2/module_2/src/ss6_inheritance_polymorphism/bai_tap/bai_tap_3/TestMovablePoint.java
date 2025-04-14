package ss6_inheritance_polymorphism.bai_tap.bai_tap_3;

public class TestMovablePoint {
    public static void main(String[] args) {
        MovablePoint mp1 = new MovablePoint();
        System.out.println("MovablePoint mặc định: " + mp1);

        MovablePoint mp2 = new MovablePoint(1.5f, 2.5f);
        System.out.println("Khởi tạo với tốc độ: " + mp2);

        MovablePoint mp3 = new MovablePoint(3.0f, 4.0f, 0.5f, 1.0f);
        System.out.println("Khởi tạo với tọa độ và tốc độ: " + mp3);

        mp3.move();
        System.out.println("Sau khi move(): " + mp3);

        mp3.setSpeed(2.0f, 2.0f);
        mp3.move();
        System.out.println("Move lần nữa với tốc độ mới: " + mp3);
    }
}

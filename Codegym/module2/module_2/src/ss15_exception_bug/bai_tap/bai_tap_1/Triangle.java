package ss15_exception_bug.bai_tap.bai_tap_1;

public class Triangle {
    private double a;
    private double b;
    private double c;

    public Triangle(double a, double b, double c) throws IllegalTriangleException {
        if (a <= 0 || b <= 0 || c <= 0) {
            throw new IllegalTriangleException("Canh tam giac phai lon hon 0!");
        }
        if (a + b <= c || b + c <= a || a + c <= b) {
            throw new IllegalTriangleException("Tong 2 canh phai lon hon canh con lai!");
        }
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public void display() {
        System.out.println("Tam giác hợp lệ với 3 cạnh: " + a + ", " + b + ", " + c);
    }
}

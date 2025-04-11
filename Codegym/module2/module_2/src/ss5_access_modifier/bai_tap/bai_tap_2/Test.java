package ss5_access_modifier.bai_tap.bai_tap_2;

public class Test {
    public static void main(String[] args) {
        // Tạo đối tượng Student
        Student student = new Student();

        student.setName("Alice");
        student.setClasses("C03");

        System.out.println(student.getInfo());
    }
}
package ss5_access_modifier.bai_tap.bai_tap_2;

public class Student {
    private String name = "John";
    private String classes = "C02";

    public Student() {
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClasses(String classes) {
        this.classes = classes;
    }

    public String getInfo() {
        return "Name: " + name + ", Class: " + classes;
    }

    // Thay đổi access modifier trong lớp Student
//    private void setName(String name) {
//        this.name = name;
//    }
//
//    private void setClasses(String classes) {
//        this.classes = classes;
//    }
}
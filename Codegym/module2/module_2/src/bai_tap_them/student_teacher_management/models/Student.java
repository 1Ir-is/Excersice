package bai_tap_them.student_teacher_management.models;

public class Student extends Person {
    private String className;
    private double grade;

    public Student(String id, String name, String gender, String dateOfBirth, String className, double grade) {
        super(id, name, gender, dateOfBirth);
        this.className = className;
        this.grade = grade;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public double getGrade() {
        return grade;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    @Override
    public String toCSV() {
        return id + "," + name + "," + gender + "," + dateOfBirth + "," + className + "," + grade;
    }

    @Override
    public String getDetails() {
        return "Student ID: " + id + ", Name: " + name + ", Gender: " + gender + ", DOB: " + dateOfBirth +
                ", Class: " + className + ", Grade: " + grade;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public static Student fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Student(parts[0], parts[1], parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
    }
}
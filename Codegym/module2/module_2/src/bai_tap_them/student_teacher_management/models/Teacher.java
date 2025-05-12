package bai_tap_them.student_teacher_management.models;

public class Teacher extends Person {
    private String subject;
    private double salary;

    public Teacher(String id, String name, String gender, String dateOfBirth, String subject, double salary) {
        super(id, name, gender, dateOfBirth);
        this.subject = subject;
        this.salary = salary;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String toCSV() {
        return id + "," + name + "," + gender + "," + dateOfBirth + "," + subject + "," + salary;
    }

    @Override
    public String getDetails() {
        return "Teacher ID: " + id + ", Name: " + name + ", Gender: " + gender + ", DOB: " + dateOfBirth +
                ", Subject: " + subject + ", Salary: " + salary;
    }

    @Override
    public String toString() {
        return getDetails();
    }

    public static Teacher fromCSV(String csv) {
        String[] parts = csv.split(",");
        return new Teacher(parts[0], parts[1], parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
    }
}

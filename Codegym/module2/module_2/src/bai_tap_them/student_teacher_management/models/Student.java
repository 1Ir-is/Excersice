package bai_tap_them.student_teacher_management.models;

public class Student extends Person {
    private String className;

    public Student(String id, String name, String dateOfBirth, String gender, String email, String phoneNumber, String className) {
        super(id, name, dateOfBirth, gender, email, phoneNumber);
        this.className = className;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    @Override
    public String getDetails() {
        return String.join("\n",
                "Student Information:",
                "Id: " + getId(),
                "Name: " + getName(),
                "Date of Birth: " + getDateOfBirth(),
                "Gender: " + getGender(),
                "Email: " + getEmail(),
                "Phone Number: " + getPhoneNumber(),
                "Class Name: " + className);
    }

    public String toCSV() {
        return String.join(",",
                getId(),
                getName(),
                getDateOfBirth(),
                getGender(),
                getEmail(),
                getPhoneNumber(),
                className);
    }

    public static Student fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Student(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6]
        );
    }
}

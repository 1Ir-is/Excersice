package bai_tap_them.case_study_furuma.models;

import java.text.DecimalFormat;

public class Employee extends Person {
    private String qualification;
    private String position;
    private double salary;

    public Employee(String id, String name, String dateOfBirth, String gender, String idCard, String phoneNuber, String email,
                    String qualification, String position, double salary) {
        super(id, name, dateOfBirth, gender, idCard, phoneNuber, email);
        this.qualification = qualification;
        this.position = position;
        this.salary = salary;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    @Override
    public String getDetails() {
        DecimalFormat df = new DecimalFormat("#,###.##");

        return String.join("\n",
                "Employee Details:",
                "ID: " + getId(),
                "Name: " + getName(),
                "Date of Birth: " + getDateOfBirth(),
                "Gender: " + getGender(),
                "ID Card: " + getIdCard(),
                "Phone Number: " + getPhoneNumber(),
                "Email: " + getEmail(),
                "Qualification: " + qualification,
                "Position: " + position,
                "Salary: " + df.format(salary)
        );
    }

    public String toCSV() {
        DecimalFormat df = new DecimalFormat("#.##");
        return String.join(",",
                getId(),
                getName(),
                getDateOfBirth(),
                getGender(),
                getIdCard(),
                getPhoneNumber(),
                getEmail(),
                qualification,
                position,
                df.format(salary)
        );
    }


    public static Employee fromCSV(String csvLine) {
        String[] parts = csvLine.split(",");
        return new Employee(
                parts[0],
                parts[1],
                parts[2],
                parts[3],
                parts[4],
                parts[5],
                parts[6],
                parts[7],
                parts[8],
                Double.parseDouble(parts[9])
        );
    }
}

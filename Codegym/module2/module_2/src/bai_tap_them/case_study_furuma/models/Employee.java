package bai_tap_them.case_study_furuma.models;

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
        return "Employee: " + getName() + ", Position: " + position + ", Qualification: " + qualification + ", Salary: " + salary;
    }
}

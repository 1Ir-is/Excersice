package bai_tap_them.student_teacher_management.services.student;

import bai_tap_them.student_teacher_management.models.Student;
import bai_tap_them.student_teacher_management.repositories.student.IStudentRepository;

import java.util.ArrayList;
import java.util.Scanner;

public class StudentService implements IStudentService {
    private final IStudentRepository studentRepository;
    private final Scanner scanner = new Scanner(System.in);

    public StudentService(IStudentRepository repository) {
        this.studentRepository = repository;
    }


    @Override
    public void add() {

    }

    @Override
    public void display() {
        ArrayList<Student> students = studentRepository.findAll();

        if (students.isEmpty()) {
            System.out.println("No student found!");
            return;
        }

        System.out.println("Student List:");
        for (Student student : students) {
            System.out.println("--------------------------------------------------");
            System.out.println("ID: " + student.getId());
            System.out.println("Name: " + student.getName());
            System.out.println("Date of Birth: " + student.getDateOfBirth());
            System.out.println("Gender: " + student.getGender());
            System.out.println("Email: " + student.getEmail());
            System.out.println("Phone Number: " + student.getPhoneNumber());
            System.out.println("Class Name: " + student.getClassName());
        }
        System.out.println("--------------------------------------------------");
    }
}

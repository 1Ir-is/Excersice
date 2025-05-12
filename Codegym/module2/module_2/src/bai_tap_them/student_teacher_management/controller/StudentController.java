package bai_tap_them.student_teacher_management.controller;

import bai_tap_them.student_teacher_management.models.Student;
import bai_tap_them.student_teacher_management.services.StudentService;
import bai_tap_them.student_teacher_management.views.StudentView;

import java.util.List;

public class StudentController {
    private final StudentService studentService = new StudentService();

    public void manageStudents() {
        boolean back = false;
        while (!back) {
            int choice = StudentView.showMenu();
            switch (choice) {
                case 1:
                    Student newStudent = StudentView.getStudentInput();
                    studentService.add(newStudent);
                    System.out.println("Student added successfully!");
                    break;
                case 2:
                    List<Student> students = studentService.getAll();
                    for (Student student : students) {
                        System.out.println(student);
                    }
                    break;
                case 3:
                    String idToDelete = StudentView.getIdToDelete();
                    studentService.delete(idToDelete);
                    System.out.println("Student deleted successfully!");
                    break;
                case 4:
                    String nameToSearch = StudentView.getNameToSearch();
                    List<Student> foundStudents = studentService.searchByName(nameToSearch);
                    for (Student student : foundStudents) {
                        System.out.println(student);
                    }
                    break;
                case 5:
                    List<Student> sortedStudents = studentService.sortByName();
                    for (Student student : sortedStudents) {
                        System.out.println(student);
                    }
                    break;
                case 6: // Return to Main Menu
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
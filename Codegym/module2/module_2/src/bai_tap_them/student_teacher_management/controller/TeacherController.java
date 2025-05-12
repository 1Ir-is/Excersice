package bai_tap_them.student_teacher_management.controller;

import bai_tap_them.student_teacher_management.models.Teacher;
import bai_tap_them.student_teacher_management.services.TeacherService;
import bai_tap_them.student_teacher_management.views.TeacherView;

import java.util.List;

public class TeacherController {
    private final TeacherService teacherService = new TeacherService();

    public void manageTeachers() {
        boolean back = false;
        while (!back) {
            int choice = TeacherView.showMenu();
            switch (choice) {
                case 1:
                    Teacher newTeacher = TeacherView.getTeacherInput();
                    teacherService.add(newTeacher);
                    System.out.println("Teacher added successfully!");
                    break;
                case 2:
                    List<Teacher> teachers = teacherService.getAll();
                    for (Teacher teacher : teachers) {
                        System.out.println(teacher);
                    }
                    break;
                case 3:
                    String idToDelete = TeacherView.getIdToDelete();
                    teacherService.delete(idToDelete);
                    System.out.println("Teacher deleted successfully!");
                    break;
                case 4:
                    String nameToSearch = TeacherView.getNameToSearch();
                    List<Teacher> foundTeachers = teacherService.searchByName(nameToSearch);
                    for (Teacher teacher : foundTeachers) {
                        System.out.println(teacher);
                    }
                    break;
                case 5:
                    List<Teacher> sortedTeachers = teacherService.sortByName();
                    for (Teacher teacher : sortedTeachers) {
                        System.out.println(teacher);
                    }
                    break;
                case 6:
                    back = true;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
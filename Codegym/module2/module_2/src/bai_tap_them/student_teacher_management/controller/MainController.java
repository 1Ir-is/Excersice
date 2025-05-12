package bai_tap_them.student_teacher_management.controller;

import bai_tap_them.student_teacher_management.views.MainView;

public class MainController {
    private final StudentController studentController = new StudentController();
    private final TeacherController teacherController = new TeacherController();

    public void start() {
        boolean exit = false;
        while (!exit) {
            int choice = MainView.showMainMenu();
            switch (choice) {
                case 1 -> studentController.manageStudents();
                case 2 -> teacherController.manageTeachers();
                case 3 -> exit = true;
            }
        }
    }
}

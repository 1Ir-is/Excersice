package bai_tap_them.student_teacher_management.views;

import bai_tap_them.student_teacher_management.utils.ValidationUtils;

public class CommonView {
    public static int getChoice(int maxOption) {
        System.out.print("Enter your choice: ");
        return ValidationUtils.validateMenuChoice(maxOption);
    }
}

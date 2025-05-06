package bai_tap_them.student_teacher_management.utils;

public class MenuPrinter {
    public static void printMainMenu() {
        System.out.println("============ Choose an option ============");
        System.out.println("1.\tManage Student.");
        System.out.println("2.\tManage Teacher.");
        System.out.println("3.\tExit.");
        System.out.println("===========================================");
    }

    public static void printStudentMenu() {
        System.out.println("========== Student Management ==========");
        System.out.println("1.\tDisplay list student.");
        System.out.println("2.\tAdd student.");
        System.out.println("3.\tEdit student information.");
        System.out.println("4.\tDelete student.");
        System.out.println("5.\tSearch student.");
        System.out.println("6.\tReturn to main menu.");
        System.out.println("========================================");
    }

    public static void printTeacherMenu() {
        System.out.println("========== Teacher Management ==========");
        System.out.println("1.\tDisplay list teacher.");
        System.out.println("2.\tAdd teacher.");
        System.out.println("3.\tEdit teacher information.");
        System.out.println("4.\tDelete teacher.");
        System.out.println("5.\tSearch teacher.");
        System.out.println("6.\tReturn to main menu");
        System.out.println("========================================");
    }
}

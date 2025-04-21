package bai_tap_them.case_study_furuma.utils;

public class MenuPrinter {

    public static void printMainMenu() {
        System.out.println("================= [ MENU ] ==================");
        System.out.println("1.\tEmployee Management");
        System.out.println("2.\tCustomer Management");
        System.out.println("3.\tFacility Management ");
        System.out.println("4.\tBooking Management");
        System.out.println("5.\tPromotion Management");
        System.out.println("6.\tExit");
        System.out.println("=============================================");
    }

    public static void printEmployeeMenu() {
        System.out.println("================= [ Employee Management ] ==================");
        System.out.println("1.\tDisplay list employees");
        System.out.println("2.\tAdd new employee");
        System.out.println("3.\tEdit employee");
        System.out.println("4.\tReturn main menu");
        System.out.println("============================================================");
    }

    public static void printCustomerMenu() {
        System.out.println("================= [ Customer Management ] ==================");
        System.out.println("1.\tDisplay list customers");
        System.out.println("2.\tAdd new customer");
        System.out.println("3.\tEdit customer");
        System.out.println("4.\tReturn main menu");
        System.out.println("============================================================");
    }

    public static void printFacilityMenu() {
        System.out.println("========== Facility Management ==========");
        System.out.println("1. Display list facility");
        System.out.println("2. Add new facility");
        System.out.println("3. Display list facility maintenance");
        System.out.println("4. Return to main menu");
        System.out.println("=========================================");
    }

    public static void printBookingMenu() {
        System.out.println("========== Booking Management ==========");
        System.out.println("1. Add new booking");
        System.out.println("2. Display list booking");
        System.out.println("3. Create new contracts");
        System.out.println("4. Display list contracts");
        System.out.println("5. Edit contracts");
        System.out.println("6. Return to main menu");
        System.out.println("========================================");
    }

    public static void printPromotionMenu() {
        System.out.println("========== Promotion Management ==========");
        System.out.println("1. Display list customers use service");
        System.out.println("2. Display list customers get voucher");
        System.out.println("3. Return main menu");
        System.out.println("==========================================");
    }

    public static void printAddNewFacilityMenu() {
        System.out.println("========== Add New Facility ==========");
        System.out.println("1. Add new villa");
        System.out.println("2. Add new house");
        System.out.println("3. Add new room");
        System.out.println("4. Back to menu");
        System.out.println("======================================");
    }
}
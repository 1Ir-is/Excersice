package bai_tap_them.electric_bill.utils;

public class ValidationUtils {

    public static boolean isValidCustomerId(String id, boolean isVietnamese) {
        String regex = isVietnamese ? "KHVN-?\\d{4}" : "KHNN-?\\d{4}";
        return id.matches(regex);
    }

    public static boolean isValidName(String name) {
        return name != null && name.matches("[a-zA-Z\\s]+");
    }

    public static boolean isValidCustomerType(String type) {
        return type != null && !type.trim().isEmpty();
    }

    public static boolean isValidPositiveInteger(String input) {
        try {
            int value = Integer.parseInt(input);
            return value > 0;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isValidNationality(String nationality) {
        return nationality != null && !nationality.trim().isEmpty();
    }
}
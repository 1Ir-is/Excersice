package ss19_String_regex.bai_tap.bai_tap_2;

public class PhoneNumberValidator {
    private static final String PHONE_NUMBER_REGEX = "^\\(\\d{2}\\)-\\(0\\d{9}\\)$";

    public static boolean isValidPhoneNumber(String phoneNumber) {
        return phoneNumber.matches(PHONE_NUMBER_REGEX);
    }

    public static void main(String[] args) {
        // Test cases
        System.out.println(isValidPhoneNumber("(84)-(0978489648)")); // true
        System.out.println(isValidPhoneNumber("(84)-(097848964)"));  // false
        System.out.println(isValidPhoneNumber("(a8)-(22222222)"));   // false
        System.out.println(isValidPhoneNumber("(84)-(22b22222)"));   // false
        System.out.println(isValidPhoneNumber("(84)-(9978489648)")); // false
    }
}

package ss19_String_regex.bai_tap.bai_tap_1;

public class ClassNameValidator {
    private static final String CLASS_NAME_REGEX = "^[CAP]\\d{4}[GHIK]$";

    public static boolean isValidClassName(String className) {
        return className.matches(CLASS_NAME_REGEX);
    }

    public static void main(String[] args) {
        System.out.println(isValidClassName("C0223G")); // true
        System.out.println(isValidClassName("A0323K")); // true
        System.out.println(isValidClassName("M0318G")); // false
        System.out.println(isValidClassName("P0323A")); // false
    }
}

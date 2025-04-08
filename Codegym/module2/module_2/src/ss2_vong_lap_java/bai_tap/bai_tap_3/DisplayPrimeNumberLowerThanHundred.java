package ss2_vong_lap_java.bai_tap.bai_tap_3;

public class DisplayPrimeNumberLowerThanHundred {
    public static boolean isPrimeNumber(int number) {
        if (number < 2) {
            return false;
        }
        for (int i = 2; i < number; i++) {
            if (number % i == 0) {
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args) {
        System.out.println("Prime numbers less than 100 are: ");
        for (int number = 2; number < 100; number++) {
            if (isPrimeNumber(number)) {
                System.out.print(number + " ");
            }
        }
    }
}

package ss2_vong_lap_java.bai_tap.bai_tap_2;

public class DisplayPrimeNumber {
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
        int count = 0;
        System.out.println("The first 20 prime numbers are: ");
        for (int number = 2; count < 20; number++) {
            if (isPrimeNumber(number)) {
                System.out.print(number + " ");
                count++;
            }
        }
    }
}

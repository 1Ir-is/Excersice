import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        double rate = 23000.0;

        Scanner scanner = new Scanner(System.in);
        System.out.print("Nhap so tien usd: ");
        double usd = scanner.nextDouble();
        double vnd = usd * rate;

        System.out.printf("So tien vnd: %.2f", vnd);
    }
}
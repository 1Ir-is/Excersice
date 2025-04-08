package ss2_vong_lap_java.bai_tap.bai_tap_1;

import java.util.Scanner;

public class DisplayType {
    public static void main(String[] args) {
        int choice = -1;
        Scanner input = new Scanner(System.in);
        while (choice != 0) {
            System.out.println("Menu");
            System.out.println("1. Draw the rectangle");
            System.out.println("2. Draw the square triangle (The corner is square at 4 different angles: top-left, top-right, bottom-left, bottom-right)");
            System.out.println("3. Draw the isosceles triangle");
            System.out.println("0. Exit");
            System.out.println("Enter your choice: ");
            choice = input.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Draw the rectangle");
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 7; j++) {
                            System.out.print("* ");
                        }
                        System.out.println();
                    }
                    System.out.println("Press Enter to go back...");
                    input.nextLine();
                    input.nextLine();
                    break;
                case 2:
                    System.out.println("Draw the square triangle");

                    System.out.println("Bottom-left");
                    for (int i = 1; i <= 5; i++) {
                        for (int j = 1; j <= i; j++) {
                            System.out.print("* ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                    System.out.println("Top-left");
                    for (int i = 5; i >= 1; i--) {
                        for (int j = 1; j <= i; j++) {
                            System.out.print("* ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                    System.out.println("Bottom-right:");
                    for (int i = 1; i <= 5; i++) {
                        for (int j = 1; j <= 5 - i; j++) {
                            System.out.print("  ");
                        }
                        for (int j = 1; j <= i; j++) {
                            System.out.print("* ");
                        }
                        System.out.println();
                    }
                    System.out.println();

                    System.out.println("Top-right:");
                    for (int i = 5; i >= 1; i--) {
                        for (int j = 1; j <= 5 - i; j++) {
                            System.out.print("  ");
                        }
                        for (int j = 1; j <= i; j++) {
                            System.out.print("* ");
                        }
                        System.out.println();
                    }
                    System.out.println();
                    System.out.println("Press Enter to go back...");
                    input.nextLine();
                    input.nextLine();
                    break;
                case 3:
                    System.out.println("Draw the isosceles triangle");
                    for (int i = 1; i <= 5; i++) {
                        for (int j = 1; j <= 5 - i; j++) {
                            System.out.print(" ");
                        }
                        for (int j = 1; j <= 2 * i - 1; j++) {
                            System.out.print("*");
                        }
                        System.out.println();
                    }
                    System.out.println("Press Enter to go back...");
                    input.nextLine();
                    input.nextLine();
                    break;

                case 0:
                    System.exit(0);
                default:
                    System.out.println("No choice!");
            }
        }
    }
}

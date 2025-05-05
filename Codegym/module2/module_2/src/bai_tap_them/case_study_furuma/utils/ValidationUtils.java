package bai_tap_them.case_study_furuma.utils;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Pattern;

public class ValidationUtils {
    private static final Scanner scanner = new Scanner(System.in);

    public static String validateInput(String regex, String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter again:");
                continue;
            }
            if (Pattern.matches(regex, input)) {
                return input;
            }
            System.out.println(errorMessage);
        }
    }

    public static boolean validateNotEmpty(String input, String errorMessage) {
        if (input == null || input.trim().isEmpty()) {
            System.out.println(errorMessage);
            return false;
        }
        return true;
    }

    public static boolean validateDateFormat(String date, String errorMessage) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate.parse(date, formatter);
            return false;
        } catch (Exception e) {
            System.out.println(errorMessage);
            return true;
        }
    }

    public static boolean validateStartBeforeEnd(String startDate, String endDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        try {
            LocalDate start = LocalDate.parse(startDate, formatter);
            LocalDate end = LocalDate.parse(endDate, formatter);

            if (start.isBefore(end)) {
                return true;
            } else {
                System.out.println("Start date must be earlier than end date!");
                return false;
            }
        } catch (Exception e) {
            System.out.println("Error parsing date. Please enter in right format!");
            return false;
        }
    }

    public static String validateDateOfBirth() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        while (true) {
            try {
                String input = scanner.nextLine();
                String[] parts = input.split("/");
                int day = Integer.parseInt(parts[0]);
                int month = Integer.parseInt(parts[1]);
                int year = Integer.parseInt(parts[2]);

                if (month == 2) {
                    boolean isLeapYear = (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
                    int maxDays = isLeapYear ? 29 : 28;
                    if (day > maxDays) {
                        System.out.println("Invalid date for February. Please try again!");
                        continue;
                    }
                }

                LocalDate date = LocalDate.parse(input, formatter);

                if (Period.between(date, LocalDate.now()).getYears() >= 18) {
                    return input;
                } else {
                    System.out.println("Age must be at least 18 years!");
                }
            } catch (Exception e) {
                System.out.println("Invalid date format, please use [dd/MM/yyyy]!");
            }
        }
    }

    public static String validateGender() {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.equalsIgnoreCase("Male") || input.equalsIgnoreCase("Female")) {
                return input;
            }
            System.out.println("Invalid gender. Please enter [Male] or [Female]!");
        }
    }

    public static int validatePositiveInt(String errorMessage) {
        while (true) {
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                System.out.println("Input cannot be empty. Please enter again:");
                continue;
            }
            try {
                int value = Integer.parseInt(input);
                if (value >= 0) {
                    return value;
                } else {
                    System.out.println(errorMessage);
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Please try again!");
            }
        }
    }

    public static double validateSalary() {
        while (true) {
            try {
                double salary = scanner.nextDouble();
                scanner.nextLine();
                if (salary > 0) {
                    return salary;
                } else {
                    System.out.println("Salary must be greater than 0!");
                }
            } catch (Exception e) {
                System.out.println("Invalid salary, please enter a valid number!");
                scanner.nextLine();
            }
        }
    }

    public static int validateMenuChoice(int max) {
        int choice;
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                if (input.isEmpty()) {
                    System.out.println("Input cannot be empty. Please enter a number between " + 1 + " and " + max + ":");
                    continue;
                }
                choice = Integer.parseInt(input);
                if (choice >= 1 && choice <= max) {
                    return choice;
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + 1 + " and " + max + ":");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid choice. Please try again!");
            }
        }
    }

    public static double validateDouble(double minValue) {
        while (true) {
            try {
                double value = Double.parseDouble(scanner.nextLine());
                if (value > minValue) {
                    return value;
                } else {
                    System.out.println("Value must be greater than " + minValue + ". Please try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again!");
            }
        }
    }

    public static int validateInt(int minValue, int maxValue) {
        while (true) {
            try {
                int value = Integer.parseInt(scanner.nextLine());
                if (value > 0 && value < 20) {
                    return value;
                } else {
                    System.out.print("Value must be from " + minValue + " to " + maxValue + ". Please try again!");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please try again!");
            }
        }
    }
}
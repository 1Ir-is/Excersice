package ss14_sort_algorithm.bai_tap.bai_tap_1;

import java.util.Scanner;

public class InsertionSortStep {
    public static void insertionSort(int[] array) {
        for (int i = 1; i < array.length; i++) {
            int currentElement = array[i];
            int j;

            System.out.println("Step " + i + ":");
            for (j = i - 1; j >= 0 && array[j] > currentElement; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = currentElement;

            for (int k = 0; k < array.length; k++) {
                System.out.print(array[k] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter the size of the array: ");
        int size = scanner.nextInt();
        int[] array = new int[size];

        System.out.println("Enter " + size + " integers:");
        for (int i = 0; i < size; i++) {
            array[i] = scanner.nextInt();
        }

        System.out.println("Original array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
        System.out.println("\n\nSorting steps:");

        insertionSort(array);

        System.out.println("\nSorted array:");
        for (int num : array) {
            System.out.print(num + " ");
        }
    }
}
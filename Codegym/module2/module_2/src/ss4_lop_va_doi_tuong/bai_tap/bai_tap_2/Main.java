package ss4_lop_va_doi_tuong.bai_tap.bai_tap_2;

import java.util.Random;

public class Main {
    public static void main(String[] args) {
        int[] array = new int[100000];
        Random random = new Random();

        for (int i = 0; i < array.length; i++) {
            array[i] = random.nextInt(100000);
        }

        StopWatch stopWatch = new StopWatch();

        stopWatch.start();
        selectionSort(array);
        stopWatch.end();
        System.out.println("Thoi gian thuc thi thuat toan selection sort la: " + stopWatch.getElapsedTime() + "ms");
    }

    public static void selectionSort(int[] arr) {
        int n = arr.length;

        for (int i = 0; i < n - 1; i++) {
            int min_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[min_idx]) {
                    min_idx = j;
                }
            }

            int temp = arr[min_idx];
            arr[min_idx] = arr[i];
            arr[i] = temp;
        }
    }
}

package ss14_sort_algorithm.bai_tap.bai_tap_2;

public class InsertionSort {
    public static void insertionSort(int[] list) {
        int pos;
        int x;
        for (int i = 1; i < list.length; i++) {
            x = list[i];
            pos = i;
            while (pos > 0 && x < list[pos - 1]) {
                list[pos] = list[pos - 1];
                pos--;
            }
            list[pos] = x;
        }
    }

    public static void main(String[] args) {
        int[] list = {5, 2, 9, 1, 7, 6};

        System.out.print("Original list: ");
        for (int num : list) {
            System.out.print(num + " ");
        }

        insertionSort(list);

        System.out.println("\nSorted list: ");
        for (int num : list) {
            System.out.print(num + " ");
        }
    }
}

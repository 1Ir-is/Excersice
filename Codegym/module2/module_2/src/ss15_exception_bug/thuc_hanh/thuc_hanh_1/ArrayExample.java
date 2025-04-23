package ss15_exception_bug.thuc_hanh.thuc_hanh_1;

import java.util.Random;

public class ArrayExample {
    public Integer[] createRandom() {
        Random random = new Random();
        Integer[] arr = new Integer[100];
        System.out.println("Danh sach phan tu cua mang: ");
        for (int i = 0; i < 100; i++) {
            arr[i] = random.nextInt();
            System.out.print(arr[i] + " ");
        }
        return arr;
    }
}

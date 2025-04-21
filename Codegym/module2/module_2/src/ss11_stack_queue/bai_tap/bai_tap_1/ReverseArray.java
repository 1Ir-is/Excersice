package ss11_stack_queue.bai_tap.bai_tap_1;

import java.util.Stack;

public class ReverseArray {
    public static void main(String[] args) {
        int[] arr = {10, 20, 30, 40, 50};

        Stack<Integer> stack = new Stack<>();

        for (int num : arr) {
            stack.push(num);
        }

        for (int i = 0; i < arr.length; i++) {
            arr[i] = stack.pop();
        }

        for (int num : arr) {
            System.out.print(num + " ");
        }
    }
}


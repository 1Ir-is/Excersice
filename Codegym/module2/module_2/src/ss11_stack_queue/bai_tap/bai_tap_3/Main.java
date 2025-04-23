package ss11_stack_queue.bai_tap.bai_tap_3;

public class Main {
    public static void main(String[] args) {
        Queue q = new Queue();
        Solution solution = new Solution();

        // Thêm phần tử
        solution.enQueue(q, 10);
        solution.enQueue(q, 20);
        solution.enQueue(q, 30);

        // Hiển thị hàng đợi
        solution.displayQueue(q);  // Output: Queue elements: 10 20 30

        // Xóa phần tử
        solution.deQueue(q);
        solution.displayQueue(q);  // Output: Queue elements: 20 30

        solution.deQueue(q);
        solution.displayQueue(q);  // Output: Queue elements: 30

        solution.deQueue(q);
        solution.displayQueue(q);  // Output: Queue is empty!
    }
}
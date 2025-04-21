package ss11_stack_queue.bai_tap.bai_tap_3;

class Solution {
    // Thêm phần tử vào queue
    public void enQueue(Queue q, int value) {
        Node newNode = new Node(value);

        if (q.rear == null) {
            q.front = q.rear = newNode;
            return;
        }

        q.rear.link = newNode;
        q.rear = newNode;
    }

    // Lấy phần tử khỏi queue
    public void deQueue(Queue q) {
        if (q.front == null) {
            System.out.println("Queue is empty!");
            return;
        }

        q.front = q.front.link;

        if (q.front == null) {
            q.rear = null;
        }
    }

    // Hiển thị queue
    public void displayQueue(Queue q) {
        if (q.front == null) {
            System.out.println("Queue is empty!");
            return;
        }

        Node temp = q.front;
        System.out.print("Queue elements: ");
        while (temp != null) {
            System.out.print(temp.data + " ");
            temp = temp.link;
        }
        System.out.println();
    }
}

package ss10_danh_sach.bai_tap.bai_tap_2;

public class MyLinkedListTest {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();

        list.addFirst("Java");
        list.addLast("Python");
        list.addLast("C++");
        list.add(1, "Kotlin");

        System.out.println("Danh sách ban đầu:");
        list.printList();

        System.out.println("Phần tử đầu tiên: " + list.getFirst());
        System.out.println("Phần tử cuối cùng: " + list.getLast());

        System.out.println("Chứa 'Python'? " + list.contains("Python"));
        System.out.println("Vị trí 'C++': " + list.indexOf("C++"));

        list.remove(2);
        System.out.println("Sau khi xoá phần tử ở vị trí 2:");
        list.printList();

        list.remove("Java");
        System.out.println("Sau khi xoá 'Java':");
        list.printList();

        MyLinkedList<String> copied = list.clone();
        System.out.println("Danh sách clone:");
        copied.printList();

        list.clear();
        System.out.println("Danh sách sau khi clear:");
        list.printList();
    }
}


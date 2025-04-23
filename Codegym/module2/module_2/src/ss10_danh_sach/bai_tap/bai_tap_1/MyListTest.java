package ss10_danh_sach.bai_tap.bai_tap_1;

public class MyListTest {
    public static void main(String[] args) {
        MyList<String> myList = new MyList<>();

        myList.add("Java");
        myList.add("Python");
        myList.add("C++");
        myList.add(1, "Kotlin");

        System.out.println("Danh sách phần tử:");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }

        System.out.println("Vị trí của 'Python': " + myList.indexOf("Python"));
        System.out.println("Có chứa 'Java'? " + myList.contains("Java"));

        myList.remove(2);
        System.out.println("Sau khi xoá phần tử ở vị trí 2:");
        for (int i = 0; i < myList.size(); i++) {
            System.out.println(myList.get(i));
        }

        myList.clear();
        System.out.println("Đã xoá toàn bộ phần tử, size = " + myList.size());
    }
}

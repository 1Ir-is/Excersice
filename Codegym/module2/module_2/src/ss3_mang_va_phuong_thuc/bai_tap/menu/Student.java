package ss3_mang_va_phuong_thuc.bai_tap.menu;

public class Student {
    private int id;
    private String name;

    public Student(String name, int id) {
        this.name = name;
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public String toString() {
        return String.format(" %-5d | %-20s", id, name);
    }
}

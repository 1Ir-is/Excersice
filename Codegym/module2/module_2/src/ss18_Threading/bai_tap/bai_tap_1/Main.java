package ss18_Threading.bai_tap.bai_tap_1;

public class Main {
    public static void main(String[] args) {
        NumberGenerator generator1 = new NumberGenerator();
        NumberGenerator generator2 = new NumberGenerator();


        Thread thread1 = new Thread(generator1, "Generator-1");
        Thread thread2 = new Thread(generator2, "Generator-2");


        thread1.setPriority(Thread.MIN_PRIORITY);
        thread2.setPriority(Thread.MAX_PRIORITY);


        thread1.start();
        thread2.start();
    }
}

package ss18_Threading.bai_tap.bai_tap_3;

public class TestPrimeFactorization {
    public static void main(String[] args) {
        Thread lazyThread = new Thread(new LazyPrimeFactorization());
        Thread optimizedThread = new Thread(new OptimizedPrimeFactorization());

        lazyThread.setPriority(Thread.MIN_PRIORITY);       // Ưu tiên thấp
        optimizedThread.setPriority(Thread.MAX_PRIORITY);  // Ưu tiên cao

        lazyThread.start();
        optimizedThread.start();
    }
}

package synchronize.synchronizeVolatile;

import java.util.concurrent.atomic.AtomicInteger;

public class AtomicMain {
    private static AtomicInteger a = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        method();
        method();
        Thread.sleep(10000);
        System.out.println(a);
    }

    public static void method() {
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                a.incrementAndGet();
            }
        });
        thread.start();
    }
}

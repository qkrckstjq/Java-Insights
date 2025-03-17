package synchronize.synchronizeVolatile;

import java.util.concurrent.TimeUnit;

public class VolatileMain {
    private static volatile boolean volatileStop = false;
    private static boolean normalStop = false;

    public static void main(String[] args) throws InterruptedException {
        method();
    }

    public static void method() throws InterruptedException {
        Thread volatileBackground = new Thread(() -> {
            for (int i = 0; !volatileStop ; i++);
            System.out.println("volatile의 background 쓰레드가 종료되었습니다!");
        });

        Thread normalBackground = new Thread(() -> {
            for (int i = 0; !normalStop; i++);
            System.out.println("noraml의 background 쓰레드가 종료되었습니다!");
        });

        volatileBackground.start();
        normalBackground.start();

        TimeUnit.SECONDS.sleep(1);

        volatileStop = true;
        normalStop = true;
    }
}

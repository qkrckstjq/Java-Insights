package synchronize.synchronizeVolatile;

public class Main {
    private static int a = 0;
    private static int b = 0;

    public static void main(String[] args) throws InterruptedException {
        countWithOutSync();
        countWithOutSync();

        countWithSync();
        countWithSync();

        Thread.sleep(10000);

        System.out.println(a);
        System.out.println(b);
    }
    public static void countWithOutSync() {
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 5000; i++) {
                a++;
            }
        });
        thread.start();
    }
    public static void countWithSync() {
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 5000; i++) {
                synchronized (Main.class) {
                    b++;
                }
            }
        });
        thread.start();
    }
}

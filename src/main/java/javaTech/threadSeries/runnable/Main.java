package javaTech.threadSeries.runnable;

public class Main {
    public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("리턴이 없는 runnable 람다식");
            throw new IllegalArgumentException("에러를 던집니다.");
        };

        Thread thread = new Thread(task);
        try {
            thread.start();
        } catch (Exception e) {
            System.out.println("에러 감지");
            throw new RuntimeException(e);
        }
    }
}

package javaTech.threadSeries.callable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;

public class Main {
    public static void main(String[] args) {
        Callable<String> task = () -> {
            return "리턴이 존재하는 Callable 람다식 ExecutorService에서 주로 사용";
        };

        ExecutorService service = Executors.newFixedThreadPool(1);
        Future<String> result = service.submit(task);
        try {
            System.out.println(result.get());
        } catch (ExecutionException | InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            service.shutdown();
        }


        ExecutorService executorService = Executors.newFixedThreadPool(3);

        List<Callable<String>> tasks = Arrays.asList(
                createTask("Task 1", 2),
                createTask("Task 2", 1),
                createTask("Task 3", 3)
        );

        try {
            System.out.println("=== invokeAll 사용 예제 ===");
            List<Future<String>> futures = executorService.invokeAll(tasks);
            for (Future<String> future : futures) {
                System.out.println(future.get());  // 블로킹해서 결과 받기
            }

            System.out.println("\n=== invokeAny 사용 예제 ===");
            String fastestResult = null;

            List<Future<String>> futures1 = new ArrayList<>();
            for(Callable<String> exec : tasks) {
                futures1.add(executorService.submit(exec));
            }

            for(Future<String> future : futures1) {
                if (fastestResult == null && future.isDone()) {
                    fastestResult = future.get();
                }
            }

            System.out.println("가장 빨리 끝난 작업 결과: " + fastestResult);

        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();  // ExecutorService 종료
        }
    }

    private static Callable<String> createTask(String taskName, int seconds) {
        return () -> {
            try {
                Thread.sleep(seconds * 1000L);  // 작업 지연 시뮬레이션
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return taskName + " 완료 (소요 시간: " + seconds + "초)";
        };
    }

}

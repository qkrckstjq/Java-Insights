package javaTech.HashMapSeries;

import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;
import java.util.concurrent.*;
import java.util.stream.IntStream;

public class Main {
    public static void main(String[] args) {
        HashMap<Integer, String> hashMap = new HashMap<>();
        Hashtable<Integer, String> hashTable = new Hashtable<>();

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        Future<Void> mapFuture = executorService.submit(task(hashMap));
        Future<Void> tableFuture = executorService.submit(task(hashTable));

        while (!mapFuture.isDone() || !tableFuture.isDone());

        executorService.shutdown();

        System.out.println(hashMap.size());
        System.out.println(hashTable.size());
    }

    public static Callable<Void> task(Map<Integer, String> map) {
        return () -> {
            IntStream.range(0, 1000).parallel().forEach((i) -> map.put(i, i + "번째 값"));
            return null;
        };
    }
}

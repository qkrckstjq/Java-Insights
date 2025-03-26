package javaTech.threadSeries.completableFuture;

import java.io.*;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {
    private static final String FILELOCATION = "src/main/java/javaTech/threadSeries/completableFuture/urls.txt";
    private static final String WRITELOCATION = "src/main/java/javaTech/threadSeries/completableFuture/html-list/";

    public static void main(String[] args) {
        checkProcessTime(process(getUrlList()));
    }

    public static HashMap<Integer, String> getUrlList() {
        HashMap<Integer, String> urlMap = new HashMap<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILELOCATION)))) {
            String line = reader.readLine();
            int idx = 1;
            while(line != null && !line.isEmpty()) {
                urlMap.put(idx, line);
                idx += 1;
                line = reader.readLine();
            }
            return urlMap;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static CompletableFuture<Void> getHtml(String url, HttpClient client, int idx, ExecutorService service) {
        HttpRequest request = HttpRequest.newBuilder(URI.create(url)).GET().build();
        return client
                .sendAsync(request, HttpResponse.BodyHandlers.ofString())
                .handleAsync((res, err) -> {
                    if (err != null || res == null || res.statusCode() != 200) {
                        makeHtmlFile(idx, "FAILED");
                    } else {
                        makeHtmlFile(idx, res.body());
                    }
                    return null;
                }, service);
    }

    public static Runnable process(HashMap<Integer, String> urlMap) {
        return () -> {
            List<CompletableFuture<Void>> futures = new ArrayList<>();
            HttpClient client = HttpClient.newHttpClient();
            ExecutorService service = Executors.newFixedThreadPool(20);

            urlMap
                    .entrySet()
                    .parallelStream()
                    .forEach((entry) -> {
                        Integer key = entry.getKey();
                        String value = entry.getValue();
                        CompletableFuture<Void> result = getHtml(value, client, key, service);

                        List<CompletableFuture<Void>> batchToJoin = null;
                        synchronized (futures) {
                            futures.add(result);
                            if (futures.size() % 100 == 0) {
                                // 리스트의 snapshot을 만들어 동기화 블록 외부에서 join() 수행
                                batchToJoin = new ArrayList<>(futures);
                                futures.clear();
                            }
                        }
                        if (batchToJoin != null) {
                            CompletableFuture.allOf(batchToJoin.toArray(new CompletableFuture[0])).join();
                        }
                    });
            CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])).join();
            service.shutdown();
        };
    }

    public static void makeHtmlFile(int idx, String html) {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(WRITELOCATION + idx + ".html", false)))) {
            writer.write(html);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void checkProcessTime(Runnable runnable) {
        long startTime = System.currentTimeMillis();
        runnable.run();
        long endTime = System.currentTimeMillis();
        System.out.println("걸린 시간 : " + (endTime - startTime));
    }
}

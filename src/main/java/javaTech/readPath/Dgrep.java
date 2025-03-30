package javaTech.readPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Stream;

public class Dgrep {
    private static final ExecutorService service = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private static final List<CompletableFuture<Void>> allProcess = new ArrayList<>();
    private static int count = 0;
    public static void main(String[] args) {
        double start = System.currentTimeMillis();
        String path = args[0];
        String keyword = args[1];
        Path curPath = Paths.get(path).toAbsolutePath();
        recursionFindFile(curPath, keyword);
        CompletableFuture.allOf(allProcess.toArray(new CompletableFuture[0])).join();
        service.shutdown();
        double end = System.currentTimeMillis();

        System.out.println("processing... " + ((end - start) / 100)  + " seconds");
        System.out.println("looking for " + count + " files");
    }

    private static void recursionFindFile(Path path, String target) {
        try(Stream<Path> pathList = Files.list(path)) {
            pathList.forEach(something -> {
                if(Files.isRegularFile(something)) {
                    count += 1;
                    allProcess.add(findKeyword(something, target));
                }

                if(Files.isDirectory(something)) {
                    recursionFindFile(something, target);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static CompletableFuture<Void> findKeyword(Path path, String target) {
        return CompletableFuture.runAsync(() -> {
            try (BufferedReader reader = Files.newBufferedReader(path)) {
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    if (line.contains(target)) {
                        System.out.println("File Name : " + path.getFileName() + ", keyword : " + i + " lines");
                        return;
                    }
                    i += 1;
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }, service);
    }
}

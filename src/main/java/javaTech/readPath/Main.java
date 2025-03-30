package javaTech.readPath;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        String path = args[0];
        String keyword = args[1];
        Path curPath = Paths.get(path).toAbsolutePath();
        recursionFindFile(curPath, keyword);
    }

    public static void recursionFindFile(Path path, String target) {
        try(Stream<Path> pathList = Files.list(path)) {
            pathList.forEach(something -> {
                if(Files.isRegularFile(something)) {
                    findKeyword(something, target);
                }

                if(Files.isDirectory(something)) {
                    recursionFindFile(something, target);
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void findKeyword(Path path, String target) {
        try (BufferedReader reader = Files.newBufferedReader(path)) {
            String line;
            int i = 0;
            while ((line = reader.readLine()) != null) {
                if (line.contains(target)) {
                    System.out.println("파일 이름 : " + path.getFileName() + ", keyword : " + i + "번째 줄");
                    return;
                }
                i += 1;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
}

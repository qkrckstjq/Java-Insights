package JavaDefaultConcept.tryWithResources;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class TryWithResourcesMain {
    private static final String readMeLoaction = "./src/main/java/JavaDefaultConcept/tryWithResources/README.md";
    public static void main(String[] args) {
        System.out.println("try-catch-finally 문법");
        tryCatchFinally();
        System.out.println("===========================");

        System.out.println("try-with-resources 문법");
        tryWithResources();
        System.out.println("===========================");
    }

    private static void tryCatchFinally() {
        BufferedReader file = null;
        try {
            file = new BufferedReader(new FileReader(readMeLoaction));
            readTxt(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (file != null) {
                try {
                    file.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private static void tryWithResources() {
        try (BufferedReader file = new BufferedReader(new FileReader(readMeLoaction))) {
            readTxt(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void readTxt(BufferedReader bufferedReader) throws IOException {
        String line = bufferedReader.readLine();
        while (line != null) {
            System.out.println(line);
            line = bufferedReader.readLine();
        }
    }
}

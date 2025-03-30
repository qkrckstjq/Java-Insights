package javaDefaultConcept.readerWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class ReaderMain {
    private static final String FILE = "./src/main/java/javaDefaultConcept/readerWriter/text.txt";

    public static void main(String[] args) {
        reading();

        inputStreamReading();

        bufferedReading();
    }

    public static void reading() {
        try(FileReader reader = new FileReader(FILE)) {
            int ch = reader.read();
            System.out.println((char) ch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void inputStreamReading() {
        try(InputStreamReader reader = new InputStreamReader(new FileInputStream(FILE), StandardCharsets.UTF_8)) {
            int ch = reader.read();
            System.out.println((char) ch);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bufferedReading() {
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(FILE), StandardCharsets.UTF_8))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        try(BufferedReader reader = new BufferedReader(new FileReader(FILE))) {
            String line = reader.readLine();
            System.out.println(line);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void objectReading() {
        try(ObjectInputStream reader = new ObjectInputStream(new FileInputStream(FILE))) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

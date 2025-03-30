package javaDefaultConcept.readerWriter;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class WriterMain {
    private static final String FILE = "./src/main/java/javaDefaultConcept/readerWriter/text.txt";

    public static void main(String[] args) {
        writing();

        inputStreamWriting();

        bufferedWriting();
    }

    public static void writing() {
        try(FileWriter writer = new FileWriter(FILE, true)) {
            String txt = "\n이건 Writer로 입력하는 String입니다부디ㅏ문이";
            writer.write(txt.toCharArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void inputStreamWriting() {
        try(OutputStreamWriter writer = new OutputStreamWriter(new FileOutputStream(FILE, true), StandardCharsets.UTF_8)) {
            String txt = "\n이건 Writer로 입력하는 String입니다부디ㅏ문이";
            writer.write(txt.toCharArray());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void bufferedWriting() {
        try(BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(FILE, true), StandardCharsets.UTF_8))) {
            String txt = "\n이건 Writer로 입력하는 String입니다부디ㅏ문이";
            writer.write(txt);
            writer.write(txt);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void objectWriting() {
        try(ObjectOutputStream writer = new ObjectOutputStream(new FileOutputStream(FILE))) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

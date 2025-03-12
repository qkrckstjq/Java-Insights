package javaDefaultConcept.stringSeries;

import java.util.ArrayList;
import java.util.List;

public class StringSeriesMain {
    public static void main(String[] args) {
        String a = new String("a");
        StringBuilder b = new StringBuilder("a");
        StringBuffer c = new StringBuffer("a");

        //String, StringBuilder, StringBuffer 비교
        System.out.println("--comparisonSeries 비교 출력값--");
        comparisonSeries(a, b, c);
        System.out.println("--끝--");

        //StringBuilder, StringBuffer 비교
        System.out.println("--comparisonBuilderBuffer 비교 출력값--");
        comparisonBuilderBuffer(b, c);
        System.out.println("--끝--");
    }

    protected static void comparisonSeries(String a, StringBuilder b, StringBuffer c) {
        Object[] strings = {a, b, c};

        print(strings);

        //추가
        a += "b";
        b.append("b");
        c.append("b");

        print(strings);

        //수정
        a = a.replace("a", "z");
        b.replace(1, 2, "z");
        c.replace(1, 2, "z");

        print(strings);

        //삭제
        a = a.replace("z", "");
        b.delete(1, 2);
        c.delete(1, 2);

        print(strings);
    }

    protected static void comparisonBuilderBuffer(StringBuilder b, StringBuffer c) {
        Object[] strings = {b, c};
        print(strings);

        List<Thread> threadList = new ArrayList<>();

        for(int i = 0; i <= 100; i++) {
            int idx = i;
            Thread curThread = new Thread(() -> {
                b.append(idx);
                c.append(idx);
            }, "curThreadName" + i);
            threadList.add(curThread);
            curThread.start();
        }

        for(Thread thread : threadList) {
            try {
                thread.join();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        print(strings);
        String string_b = b.toString();
        String string_c = c.toString();
        System.out.println(string_b.length());
        System.out.println(string_c.length());
    }

    protected static <T> void print(T[] strings) {
        for(T s : strings)
            System.out.println(s);
        System.out.println("=====================");
    }
}

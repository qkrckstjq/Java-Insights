package javaTech.customKeyStore;

import jdk.jshell.Snippet;

import javax.net.ssl.SSLEngineResult;
import java.io.*;
import java.time.Duration;
import java.util.HashMap;

public class Main {
    public static void main(String[] args) {
        CustomKeyStore<String> keyStore = new CustomKeyStore<>();

        keyStore.SET("테스트", "잘 저장이 되나?");

        keyStore.EXPIRE("테스트", 10);

        try {
            System.out.println("스레드 정지 전 : " + keyStore.GET("테스트"));
            Thread.sleep(Duration.ofSeconds(10).toMillis());
            System.out.println("스레드 정지 후 삭제가 잘 됐나? :" + keyStore.GET("테스트"));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        keyStore.SET("스케쥴 체크1", "하이");
//        keyStore.EXPIRE("스케쥴 체크1", 1);

        keyStore.SET("스케쥴 체크2", "하이");
//        keyStore.EXPIRE("스케쥴 체크2", 1);

        keyStore.SET("스케쥴 체크3", "하이");
//        keyStore.EXPIRE("스케쥴 체크3", 1);

//        try {
//            keyStore.keys();
//            Thread.sleep(Duration.ofSeconds(5).toMillis());
//            keyStore.keys();
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
    }
}

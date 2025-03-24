package javaTech.threadSeries.reenTrantLock;

import java.util.concurrent.locks.ReentrantLock;

public class Main {
    public static void main(String[] args) {
        ReentrantLock reentrantLock = new ReentrantLock();
        if (reentrantLock.tryLock()) {
            try {
                // 락 획득 성공한 경우
            } finally {
                reentrantLock.unlock();
            }
        } else {
            // 락 획득 실패 시 처리할 내용
        }
    }
}

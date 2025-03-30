package javaTech.customKeyStore;

import java.io.*;
import java.time.Duration;
import java.util.Map;
import java.util.concurrent.*;

public class CustomKeyStore<T extends Serializable> {
    private final Map<String, Value> hashMap;
    private final ScheduledExecutorService scheduleService;
    private final String FILE_LOCATION = "./src/main/java/javaTech/customKeyStore/file.txt";

    public CustomKeyStore() {
        hashMap = new ConcurrentHashMap<>();
        scheduleService = Executors.newScheduledThreadPool(2);
        scheduleRemoveExpireValue();
    }

    public T GET(String key) {
        Value existValue = hashMap.get(key);
        if(existValue == null) {
            return null;
        }

        if(existValue.isExpired()) {
            hashMap.remove(key);
            return null;
        }

        return existValue.value;
    }

    public void SET(String key, T value) {
        Value existValue = hashMap.get(key);
        long now = System.currentTimeMillis();
        if(existValue == null) {
            Value newValue = new Value(value, -1, now);
            hashMap.put(key, newValue);
            return;
        }
        existValue.setSaveAt(now);
        existValue.setValue(value);
    }

    public void EXPIRE(String key, long seconds) {
        Value existValue = hashMap.get(key);
        if(existValue == null) {
            throw new NullPointerException("존재 하지 않는 키 입니다.");
        }
        existValue.setExpire(Duration.ofSeconds(seconds).toMillis());
    }

    public void scheduleRemoveExpireValue() {
        scheduleService.scheduleAtFixedRate(() -> {
            hashMap.forEach((key, value) -> {
                if (value.isExpired()) {
                    hashMap.remove(key);
                }
            });
        }, 1, 1, TimeUnit.SECONDS);
    }


    private class Value implements Serializable{
        private T value;
        private long expire;
        private long saveAt;

        public void setValue(T value) {
            this.value = value;
        }

        public void setExpire(long expire) {
            this.expire = expire;
        }

        public void setSaveAt(long saveAt) {
            this.saveAt = saveAt;
        }

        public Value(T value, long expire, long saveAt) {
            this.value = value;
            this.expire = expire;
            this.saveAt = saveAt;
        }

        public boolean isExpired() {
            long now = System.currentTimeMillis();
            return expire >= 0 && (saveAt + expire) < now;
        }
    }

    public void keys() {
        System.out.println("현재 키 개수 : " + hashMap.size());
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_LOCATION))) {
            out.writeObject(hashMap);
            System.out.println("데이터 저장 완료: " + FILE_LOCATION);
        } catch (IOException e) {
            System.err.println("데이터 저장 실패: " + e.getMessage());
        }
    }

    private void loadFromFile() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(FILE_LOCATION))) {

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

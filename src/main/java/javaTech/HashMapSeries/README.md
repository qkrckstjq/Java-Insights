# HashMap, HashTable

---

**HashMap**은 동기화를 지원하지 않는다.

**HashTable**은 동기화를 지원한다.

**HashMap**은 멀티 스레드 환경에서 원치 않는 결과를 얻을 수 있다.

```java
public V put(K key, V value) {
        return putVal(hash(key), key, value, false, true);
}

public synchronized V put(K key, V value) {
    // Make sure the value is not null
    if (value == null) {
        throw new NullPointerException();
    }

    // Makes sure the key is not already in the hashtable.
    Entry<?,?> tab[] = table;
    int hash = key.hashCode();
    int index = (hash & 0x7FFFFFFF) % tab.length;
    @SuppressWarnings("unchecked")
    Entry<K,V> entry = (Entry<K,V>)tab[index];
    for(; entry != null ; entry = entry.next) {
        if ((entry.hash == hash) && entry.key.equals(key)) {
            V old = entry.value;
            entry.value = value;
            return old;
        }
    }

    addEntry(hash, key, value, index);
    return null;
}
```
둘 코드의 차이점으로
HashMap은 키, 밸류 모두 null값을 허용한다.

HashTable은 키, 밸류 모두 null값을 허용하지 않는다.
또한 **synchronized** 키워드가 붙어있어서 동기화를 지원한다.

HashTable에는 Synchronized 키워드가 붙어있기 때문에 성능이슈가 발생할수 있다.

따라서 **ConcurrentHashMap**으로 **HashTable**과 달리 부분적으로 **Synchronized**를 걸어 HashTable보다 좋은 성능을 제공한다.
# String, StringBuilder, StringBuffer

---

## String
String 타입은 불가변성의 특징을 가지고 있다. (값 수정 불가능)

**상수 문자열에 적합하다.**

---

## StringBuilder
StringBuilder 타입은 가변성의 특징을 가지고 있다. (delete, append 등 지원)
비동기 처리를 지원하지 않는다.
여러 스레드가 동시에 문자열을 수정하려고 하면 특정 스레드의 작업이 누락되거나 덮어씌어지면서
모든 작업이 온전하게 처리되지 않을 수 있음

**문자열의 수정이 빈번하고 단일 스레드 환경에서 적합**

---

## StringBuffer
StringBuffer 타입은 가변성의 특징을 가지고 있다.
StringBuilder와는 달리 멀티 스레드 환경에서 하나의 스레드가 변경하기 위해 접근하면
해당 StringBuffer를 lock을 걸어 비동기 처리를 지원한다.

**문자열의 수정이 빈번하고 멀티 스레드 환경에서 적합**

---

## StringBuilder, StringBuffer의 각 append, delete 비교
```java
//StringBuilder
@Override
public StringBuilder append(char[] str, int offset, int len) {
    super.append(str, offset, len);
    return this;
}
@Override
public StringBuilder delete(int start, int end) {
    super.delete(start, end);
    return this;
}
```

```java
//StringBuffer
@Override
@IntrinsicCandidate
public synchronized StringBuffer append(int i) {
    toStringCache = null;
    super.append(i);
    return this;
}
@Override
public synchronized StringBuffer delete(int start, int end) {
    toStringCache = null;
    super.delete(start, end);
    return this;
}
```
실제 메소드에 Syncronized 차이가 있다.
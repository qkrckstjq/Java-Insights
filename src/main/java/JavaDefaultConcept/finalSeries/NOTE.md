# final, finally, finalize

## final
변수 앞에 붙일 수 있는 키워드로 해당 키워드 붙은 변수는 불가변성을 띈다.
상황에 따라 다르지만,
한 클래스 내에서 선언하고 생성되는 인스턴스에 상관없이 모두 같은 값을 사용한다면

**static 변수**
```java
private static final int val = 10; 
```
와 같이 static을 붙여 모든 인스턴스가 같은 값을 사용하게 할 수도 있고,

---

**인스턴스 변수**
```java
public class FinalVar {
    private final int val;    
    public finalVar (int val) {
        this.val = val;
    }
}
```
위 처럼 선언하여 각 인스턴스마다 초기값을 설정할 수 도 있다.

---

**지역 변수**

아니면 해당 메소드만 사용하는 특정값을 메소드 내부에서 선언하여 상수처럼 사용 할 수 있게 지역 변수로도 선언 가능하다.
```java
private void method() {
    final int var = 10;
}
```

---

## finally

try-catch문법에서 사용 할 수 있는 코드로
try-catch문법에서 성공, 실패 여부 상관없이 실행되는 문법이다.

`프로그램 강제 종료와 같은 상황에서는 실행되지 않음`

파일을 열고 닫을때, try, catch에 상관없이 안전하게 파일과의 연결을 끊는 역할을 할때 사용하며,
주로 뭔가 안정적으로 마지막에 마무리하는 작업이 필요할때 사용한다.

try나 catch에 return이 있어도 finally 내용이 먼저 실행된 후에 try, catch 내부 return이 작동하고,
finally에 return이 있을경우 try, catch의 return은 무시된다.

`(finally 내부에는 자체적인 return이 존재함, 허나 원하는 값으로 리턴 가능)`

---

## finalize
자바 9부터 사장(deprecated)되어 사용하지 않는 문법으로
GC가 해당 객체를 폐기 처분하기전 마지막으로 호출하여 마무리 작업을 진행할 수 있게한다.

GC가 반드시 원하는대로 작동하는것이 아니라 불안전한 기술이라 사장된거 같다.

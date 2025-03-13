# OverLoading, OverRiding

---

## OverLoading
오버로딩은 클래스의 생성자 함수나 메소드를 하나의 형태만 존재하는것이 아닌
여러 형태의 인자를 받을 수 있도록 설계 가능한 방법이다.

```java
public class Over {
    public Over() {}
    public Over(int a) {
        //...
    }
    public Over(String a) {
        //...
    }
}
```
위 처럼 하나의 생성자 함수만 존재하는것이 아닌 여러 형태의 생성자 함수를 생성할 수 있다.

---

## OverRiding

오버라이딩은 자식 클래스가 부모 클래스를 상속받아 메소드를 재정의 할때 사용하는 어노테이션이다.

부모 클래스에서 정의된 메소드의 리턴타입, 접근제어 수준, 인자등 부모 메소드의 구성은 변경할 수 없지만,
내부 로직을 변경할 수 있다.

```java
public class Parents {
    public void hello() {
        System.out.println("hello");
    }
}

public class Child extends Parents {
    @Override
    public void hello() {
        System.out.println("hi");
    }
}
```
위는 부모 클래스에서 정의된 `"hello"` 메소드를 자식 클래스에서 재정의한 예시이다.

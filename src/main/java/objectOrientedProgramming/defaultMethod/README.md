# default 메소드

자바 8 이후에 생긴 키워드로 인터페이스에서 사용가능한 메소드 키워드이다.

원래 인터페이스에는 구체적인 메소드 구현이 불가능했었다.

만약 인터페이스에 추가적인 추상 메소드를 기술하게 되면 해당 인터페이스를 구현한 클래스들 전부 수정해줘야 하는 문제가 생긴다.

이 default 메소드를 사용하면 기존의 클래스들도 변함없이 사용 가능하다.

```java
public interface A {
    default void hello() {
        System.out.println("hello");
    }
}

public class C implements A{
    
}

public static void main(String[] args) {
    C c = new C();
    c.hello();
}
```

default는 해당 인터페이스를 구현한 모든 클래스에서 사용할 수 있다.

예외로 다중 인터페이스를 구현했을때 똑같은 default 메소드가 존재한다면 @Override를 통해 두 인터페이스 메소드드 중 하나를 택하거나 구현하면 된다.
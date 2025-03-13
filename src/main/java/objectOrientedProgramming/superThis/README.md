# super, this

## super

super는 상속받은 자식 클래스가 부모 클래스의 필드, 메소드, 생성자를 호출할때 사용한다.

부모 클래스에서 선언된 값들을 불러온다고 부모 클래스의 값이 아니라

자식 클래스가 가지고 있지만 부모 클래스에서 정의를 참조할때 super를 사용한다.

```java
public class Parents {
    private final String name;
    
    public Parents(String name) {
        this.name = name;
    }
    
    public void printName() {
        System.out.println(this.name);
    }
}

public class Child extends Parents{
    public Child(String name) {
        super(name);
    }
    
    public void printSuperName() {
        super.printName();
        System.out.println("hihi");
    }
}
```

위 Child 클래스는 super()를 사용하여 부모 클래스에서 정의된 생성자 함수를 호출하여 사용했고,
메소드또한 부모 클래스에서 정의된 메소드와 추가적으로 sout 메소드를 구현했다.

---

## this

this는 클래스가 본인을 가르키는 키워드이다.

```java
import objectOrientedProgramming.superThis.Parents;

public class Child {
    private final String name;

    public Child(String name) {
        this.name = name;
    }

    public void printHi() {
        System.out.println("hi");
    }
    
    public void printHelloHi() {
        System.out.println("hello");
        this.printHi();
        printHi();
    }
}
```

위에서는 생성자 함수에서 인자로 받은 변수와 본인 필드의 변수명이 똑같지만,
this 키워드를 통해 각각 다르게 인지했다.

또한 메소드에서 본인 클래스의 또다른 메소드를 호출하여 본인의 메소드를 활용했다.

기본적으로 this를 붙이지 않아도 본인 클래스에서 선언된 필드, 메소드를 기입하면
자동으로 본인의 클래스에서 찾아 할당한다.

메소드로 받는 매개변수와 필드값이 비슷할때 주로 사용한다.
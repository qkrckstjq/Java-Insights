# clone과 얕은 복사 깊은 복사

---

## clone

자바에서 clone은 클래스의 복사를 말한다.

1. 복사를 할 클래스는 Cloneable 인터페이스를 구현해야한다.<br>
(Cloneable은 아무런 메소드도 선언되지 않는 텅 빈 인터페이스, 이 인터페이스가 없다면 clone시 CloneNotSupported 에러 생김)
2. 복사를 할 클래스는 Object의 protected로 선언된 clone 메소드를 오버라이딩 하거나, 클래스 내부적으로 사용한다.
3. clone 메소드는 try-catch로 감싸거나 메소드에 throw CloneNotSupportedException 키워드를 추가해야한다.

```java
package objectOrientedProgramming.clone;

import java.util.ArrayList;
import java.util.List;

public class Person implements Cloneable{
    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("p1");
        Person p2 = (Person) p1.clone();
    }
}
```

---

## 얕은 복사, 깊은 복사

clone은 얕은 복사로 작동한다.

클래스내에 원시 타입으로 선언된 필드값들은 완전한 복사가 진행되지만, 

참조 타입으로 선언된 필드는 얕은 복사가 시행된다.

```java
public class Person implements Cloneable{
    private final String name;
    private final List<String> childrens = new ArrayList<>(List.of("김", "이", "박"));

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void printChildrens() {
        for (String child : childrens) {
            System.out.println(child);
        }
    }

    public void addChild(String child) {
        this.childrens.add(child);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("p1");
        Person p2 = (Person) p1.clone();

        System.out.println(p1 == p2);

        p2.addChild("안녕");

        p1.printChildrens();
        System.out.println();
        p2.printChildrens();
    }
}
```

위 Person 클래스에서 List<String>으로 선언된 필드는 얕은 복사만 수행이 되어
clone이 진행된 이후에 복사된 클래스에 addChild로 List<String>에 값을 추가를 하면
p1이 참조하고 있는 List<String>와 동일한 객체로 둘 다 동일한 List<String>을 공유한다.


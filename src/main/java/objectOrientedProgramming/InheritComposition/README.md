# 상속과 합성

---

## 상속
상속은 클래스에서 extends 키워드를 사용하여 부모 클래스의 모든 요소를 물려받는다.
이는 강한 결합도를 발생시킨다.

상속은 컴파일 과정에서 관계, 의존성이 정해진다.

또한 자식 클래스는 부모 클래스에서 구현된 동작을 알고있어야 한다.

코드의 중복을 없애고 객체간 의존도, 결합도를 낮추는 좋은 방법같지만,
클래스가 늘어날수록 클래스간 결합도, 의존도가 폭발적으로 상승하는 문제를 만들기도 한다.

상속의 안좋은 예시로 실제로 자바는 Stack 클래스가 존재하는데 이 Stack 클래스는 Vector 클래스를 상속받는다.
```java
public class Vector<E>
        extends AbstractList<E>
        implements List<E>, RandomAccess, Cloneable, java.io.Serializable
{
    public synchronized boolean add(E e) {
        modCount++;
        add(e, elementData, elementCount);
        return true;
    }

    private void add(E e, Object[] elementData, int s) {
        if (s == elementData.length)
            elementData = grow();
        elementData[s] = e;
        elementCount = s + 1;
    }
}

public class Stack<E> extends Vector<E> {
    public E push(E item) {
        addElement(item);

        return item;
    }
}


Stack<Integer> stack = new Stack<>();
stack.push(item);
stack.add(item);
```

위 코드는 add, push 중복되는 것 같은 메소드가 사용중이다.

push 메소드는 Stack 클래스에서 구현한 메소드이다.

하지만 add 메소드는 Stack 클래스에 존재하는 메소드가 아닌 상속받은 Vector 클래스에 존재하는 메소드로
Stack 클래스 기준 Vector의 add 메소드를 무시할 수 없어서 이중으로 비슷한 메소드가 노출되고 있다.

실제로 push는 동작 후 해당 item을 리턴하지만, add는 동작 후 true를 리턴한다.

비슷하지만 실제로는 작동방식이 조금 다르고, 혼용되서 사용할 수 있기에
stack의 사용을 권장하지 않는다. `(deque로 대신 권장)`

---

## 합성
합성은 클래스를 상속하지 않고 클래스안에서 다른 클래스를 필드로 선언하여 필요한 기능들만 사용하는 방식이다.

합성하고자 하는 클래스의 결함, 문제점을 그대로 상속받지 않아 결합도 측면에서도 낮아진다.

합성은 런타임 과정에서 의존성과 관계가 정해진다.

흔히 스프링의 DI`(의존성 주입)`이 조금 더 개선된 합성의 방식이다.

```java
public class Stack<E> {
    private Vector<E> elements = new Vector<>(); // 합성

    public E push(E item) {
        elements.addElement(item);
        return item;
    }
}
```
위 방법처럼 합성으로 해결한다면, Stack에서 add를 호출할 수도 없으며, add라는 것의 존재도 알 수 없다.
단지 Stack 클래스는 합성한 Vector의 addElement메소드를 사용하여 새로운 push 메소드를 생성한 것이다.
---

상속은 부모 자식, 수직 관계, is ~a관계라고도 표현한다.
합성은 형제, 수평 관계, has ~a관계라고도 표현한다.






# instanceof 연산자의 사용과 단점

---

## 사용
instanceof는 객체의 종류를 비교하여 boolean 혹은 캐스팅 된 객체를 리턴해주는 키워드이다.

```java
private void method(Object a) {
    if (a instanceof String word) {
        System.out.println(word);
    }
}
```
위 코드는 a의 타입을 method가 **직접** String타입과 비교하여 같은 타입이라면 word 변수에 저장하고
그 변수를 출력하고 있다.

## 단점
instanceof 몇가지 단점이 존재한다.
우선 다음과 같은 객체에서
```java
public interface Animal {
    void sound();
}

public class Cat implements Animal{
    @Override
    public void sound() {
        System.out.println("냐옹");
    }
}
public class Dog implements Animal{
    @Override
    public void sound() {
        System.out.println("멍멍");
    }
}
public class InstanceOfKeyWordMain {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        SoundInstanceOf(dog);
        SoundInstanceOf(cat);

        SoundInterface(dog);
        SoundInterface(cat);
    }

    private static void SoundInstanceOf(Animal animal) {
        if (animal instanceof Dog dog) {
            dog.sound();
        } else if (animal instanceof Cat cat) {
            cat.sound();
        }
    }

    private static void SoundInterface(Animal animal) {
        animal.sound();
    }
}
```

`SoundInstanceOf` 메소드는 구체적인 타입(Dog, Cat)을 직접 검사하고, 해당 타입에 맞게 행동(소리 내기)을 수행합니다.

반면, `SoundInterface` 메소드는 Animal 인터페이스에 선언된 sound() 메소드를 호출하여 다형성을 활용합니다.

##instanceof 사용의 단점

1. 유지 보수의 단점

- 동물이 추가될때마다 새로운 if else 문이 추가되어야 한다.
- 코드가 길어지고, 변경 시 일일이 수정해야 한다.

2. 객체 단일 원칙에 위배

- 객체는 본인 객체 이외의 다른 객체에 대해 책임을 알 필요도, 가질 필요도 없다.<br>
- 위 상황은 InstanceOfKeyWordMain 클래스가 Animal 인터페이스의 구현체 클래스들 전부를 직접 비교하고<br>
행동도 직접 해당 동물의 소리를 내라고 구체적으로 지시하면서 단일 책임 원칙에 위배된다.

3. 추상화를 지키지 못했다.
- 2번과 연결되는 문제로 Animal 클래스를 사용하려고하는 외부 클래스 InstanceOfKeyWordMain 클래스는 Animal<br>
클래스의 하위 클래스들에 대해 알 필요가 없다.
- 외부 클래스가 인터페이스의 하위 클래스들의 존재 및 구체적인 내용을 알필요가 없는 추상화의 목적인 훼손되는 형태이다. 

---

##interface 사용시 장점
1. 확장성

새로운 동물 타입을 추가해도 Animal interface 상속시 기존 interfaceSound 메소드 변경 필요 x

2. 객체 책임 분리

각 객체가 본인의 역할만 담당하게 되면서 외부 클래스가 Animal의 하위 클래스들의 구체적인 정보를 알 필요가 없어짐

3. 추상화 유지

외부 클래스가 하위 클래스를 알 필요가 없어지면서 Animal 인터페이스만을 가지고 동작하면 됌

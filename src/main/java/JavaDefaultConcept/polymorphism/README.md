# 다형성

---

다형성이란 자바에서 추상 클래스, 인터페이스를 통해 구체적이지 않은 추상적인 동작들을 미리 서술하고
이후 이 추상화된 동작들을 상속 이나 구현하여 객체간 결합도나 책임을 분리시키는 방법이다.

## abstract

abstract 키워드는 클래스를 만들때 사용 할 수 있는 키워드로
해당 키워드가 붙은 클래스는 **추상 클래스**라고 부른다.

**추상 클래스는** 여러 특징이 있는데 다음과 같다.

1. 클래스가 추상 클래스를 다중으로 상속받는 것이 불가능하다.
2. 추상 메소드뿐만 아니라 구체적인 동작을 기술할 수 있다.
3. 추상 클래스내에 필드를 선언하여 사용 할 수 있다.<br>`(상속할때 필드값은 필수 상속 대상이 아님)`
4. 추상 클래스 자체의 인스턴스 생성은 불가능하지만 생성자 함수 구현을 상속시킬수는 있다.

---

## interface

interface는 클래스가 아닌 인터페이스타입의 객체로
class 대신 interface로 대체한다.

**interface**의 특징은 다음과 같다.

1. 구체적인 동작 기술을 할 수 없이 모두 추상적인 메소드만 기술 가능하다.
2. 필드값을 선언할 수 있느나, static한 필드만 선언 가능하다.
3. 클래스가 인터페이스를 구현할때 다중 인터페이스를 동시에 구현 가능하다. <br> `(구현하고자 하는 모든 인터페이스의 추상 메소드 전부 구현해야함)`
4. 인터페이스의 인스턴스는 따로 생성할 수 없다.

* 자바 8 이후에는 default, static 키워드를 통해 구체적인 동작 기술 가능<br>
> default : 해당 인터페이스를 상속하는 모든 클래스 사용가능, 클래스에서 오버라이딩 가능
> static : 해당 인터페이스자체를 통해 사용가능

---

## abstract + interface

추상 클래스와 인터페이스는 서로 결합하여 사용 할 수 있다.

두 가지의 차이점은 느끼기에

넓은 범위에서의 동작의 중복보다 좁은 범위에서 공통된 동작 많고<br>
좁은 범위보다는 넓은 범위에서 공통으로 가질 수 있는 행동이 많기 때문에

> 동작 : 작고 기계적인 움직임, 구체적인 action <br>
> 행동 : 여러 동작이 모인 활동 ex) 숨어서 이동, 요리하기 등

추상 클래스는 인터페이스보다 좁은 범위의 추상화 전략에 어울린다.

인터페이스는 추상 클래스보다 넓의 범위의 추상화 전략에 어울린다.

즉 동물을 표현하고자 할때 모든 동물들이 공통적으로 가지지만 구체적인 내용이 다른 

"먹는다"와 같은
동작은 Interface에서 기술하는 것이 더 잘 어울리고

하나의 종 즉 "개", "새", "물고기"와 같은 종의 종류는 같은 종에서 일치하는 메커니즘으로 작동하는 동작들을
abstract를 통해 구현하면 더욱 효과적이다.

또한 Interface의 특징이 다중 구현을 통해<br>
Animal 보다 상위 개념이 생명체의 공통적인 특징들을 Interface를 통해 다중으로 구현하여 Animal에 기술하면 더욱 적절하다.

## 예시

### Animal interface 
```java
public interface Animal {
    static final boolean dieAble = true;
    void sound();
}
```
동물들의 공통적인 동작으로 울음소리`sound`를 추상화 시킴

---

### Bird abstract
```java
public abstract class Bird implements Animal{
    String name;

    void fly() {
        System.out.println(name + " can fly");
    }

    abstract void run();

    public Bird(String name) {
        this.name = name;
    }
}
```
Animal 인터페이스를 구현하여 새들의 특징인 fly를 구체적으로 기술<br>
반대로 뛰는 동작은 추상화하여 기술함<br>
또한 필드값과 생성자 함수도 구체적으로 기술하여 넘김

---

### Eagle
```java
public class Eagle extends Bird {
    public Eagle(String name) {
        super(name);
    }

    @Override
    void run() {
        System.out.println("eagle can run");
    }

    @Override
    public void sound() {
        System.out.println("독수리 울음소리");
    }
}
```

Bird를 상속했고 생성자 함수는 super를 사용해서 부모 클래스(추상 클래스)의 생성자 함수 상속

추상 클래스의 run 메소드를 구체적으로 구현함

인터페이스 sound 메소드를 구체적으로 구현함






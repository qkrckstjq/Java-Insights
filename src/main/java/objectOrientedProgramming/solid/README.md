# SOLID

## S : 단일 책임 원칙

### Single-Responsibility-Principle

각 클래스는 한가지 기능만을 책임져야 한다.

```java
public class UserService {
    public void createUser() {
        //....
    }
    
    public void sendMessage() {
        //....
    }
}
```
위는 단일 책임 원칙을 위반했다.

유저와 관련된 행동이 아니기 때문에 분리하여 작업해야한다.

```java
public class UserService {
    public void createUser() {
        //....
    }
}
public class MessageService {
    public void sendMessage() {
        //....
    }
}
```
유저와 메세지 기능을 분리하여 각 클래스가 각자의 기능을 책임지고 있다.

---

## O : 개방 폐쇄 원칙

### Open-Closed-Principle

변경에는 폐쇄적이고 확장에는 열려있어야 한다는 원칙이다.

상속, 추상화, 합성을 활용하여 기능 추가, 변경 시 유연하게 코드를 수정, 추가 해야 한다.

---

## L : 리스코프 치환 원칙

### Listov-Substitution-Principle

자식 클래스는 부모기반의 클래스들로 교체가능해야 한다는 원칙이다.

```java
public void LSP() {
    Collection data = new LinkedList();
    data = new HashSet();

    modify(data);
}

public void modify(Collection data) {
    list.add(1);
}
```
부모의 클래스를 타입으로 받으면서`(업캐스팅 된 상태)`에서 부모 클래스의 메소드를 사용했을때 작동되어야 한다는 원칙이다.

---

## I : 인터페이스 분리 원칙

### Interface-Segregation-Principle

SRP는 클래스의 단일 책임 원칙이라면 ISP는 인터페이스의 단일 책임 원칙이다.

---

## D : 의존 역전 원칙

### Dependency-Inversion-Principle

클래스간 참조에서 상대 클래스의 추상 클래스나 인터페이스에 의존해야 한다.

쉽게 변할 수 있는 클래스보다 쉽게 변하지 않는 상위에 존재하는 추상 클래스, 인터페이스를 참조해야 한다는 원칙



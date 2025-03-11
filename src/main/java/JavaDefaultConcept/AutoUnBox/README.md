# Autoboxing, UnBoxing

## Autoboxing
자바에서 원시 타입의 값을 자동으로 객체로 감싸주는 것을 말한다.
```java
Integer a = 5;
//new Integer는 더이상 사용하지 않지만
//Integer a = new Integer(5);를 자동으로 취해줌
```

## UnBoxing
반대로 객체 타입을 원시 타입으로 풀어내는 것을 말한다.

```java
Integer a = 10;
int b = a;
//Integer 객체로 선언된 a를 int타입의 원시 변수 b에 자동으로 풀어서 넣어주는 모습
```

Integer의 경우 new Integer() 사용을 못하기 때문에 당연히 사용해야 하지만
이 boxing 과정이 많이 발생하면 단점이 생기는데
추가적인 연산이 생긴다는 점과 객체의 생성과 제거의 빈번은 GC의 성능에도 영향을 끼칠 수 있다.

극단적인 예시로

```java
Integer a = 5;
for (int i = 1; i < 1000; i++) {
    a += i;
}
```
위와 같은 상황에서는 객체로 선언된 a에 원시타입의 i를 더하기 위해
매 연산마다 Integer를 int형으로 unboxing 시켜서 i와 더하고 다시 autoboxing을 하고 있다.

Integer 말고도 boolean을 포함하여 다양한 Number의 하위 클래스들 및 char에서는 이 boxing이 일어나기 때문에
원시타입과 참조타입간의 빈번한 연산이 발생하는 경우는 사용을 피해야 한다.

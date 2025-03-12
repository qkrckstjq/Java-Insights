# passByValue

---

객체지향 프로그래밍 언어에서는 크게 두가지

### passByValue | passByReference
가 존재한다.

메소드, 함수에게 인자를 넘길때 해당 인자의 값만 넘기는 것 인지, 해당 인자의 참조값 자체를 넘기는 것인지에 대한 시각이다.

자바는 passByValue이다.
원시 타입을 인자로 넘기게 된다면 해당 원시 타입의 인자의 복사값을 넘긴다.

즉 넘어간 인자를 변경해도 원본 원시 타입 인자의 값은 변하지 않는다.

```java
int a = 5;

changePrimitiveValue(a);

public static void changePrimitiveValue(int num) {
    num = 10;
}
```
위 경우 a는 메소드에 들어가기 전에도 5, 들어간 이후에도 5를 유지한다.

객체의 경우 passByValue는 참조값을 복사하여 인자로 넘긴다.
즉 메소드 자체에서 사용되는 지역 변수에게 넘기는 인자의 주소값을 복사하여 보내는 것이다.

참조값이 동일함으로 인자안에서 객체 내부의 값을 변경하면 실제로 객체의 값이 변경되는것이 적용된다.
```java
StringBuilder b = new StringBuilder("b");
changeReferenceValue(b);
public static void changeReferenceValue(StringBuilder str) {
    str.append("c");
    str = new StringBuilder("zxc");
}
```
위 상황을 보면 메소드 내부에서 인자로 넘어온 StringBuilder에게 "c"를 더하고 있다.
실제로 동일한 객체를 변경하고 있음으로 값이 변경된다.
하지만 메소드 지역 변수로 사용중인 str에 새로운 StringBuilder를 부여한다 해서 외부의 b 변수의 값이 바뀌지는 않는다.

### 메소드 진입 전 <br>
변수 b -> StringBuilder

### 메소드 진입 <br>
변수 b -> StringBuilder <- str

```java
str.append("c"); //b.append("c")와 동일
```
b와 str은 동일한 StringBuilder 참조중

```java
str = new StringBuilder("zxc"); //새로운 StringBuilder를 생성하여 str이 참조하게 변경
```
변수 b는 동일한 StringBuilder 참조 `(참조하는 대상이 변경되지 않음)`

변수 str은 새로운 StringBuilder 참조 `(참조하는 대상이 바뀜, 메소드가 종료되면 새로운 StringBuilder또한 제거됨)`



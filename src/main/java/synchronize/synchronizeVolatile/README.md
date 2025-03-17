> 동시성
> 가시성
> 원자성
> 문제 상황
> volatile
> synchronized
> Atomic

# 동시성
여러 스레드가 하나의 변수에 접근하여 생기는 문제를 **동시성** 문제라고 한다.

**동시성**문제로 인해 발생하는 또 다른 문제로는 **원자성**이 지켜지지 않는다거나 **가시성**이 지켜지지 않는다는 문제들이 있다.


# 가시성
여러 스레드가 하나의 변수에 대해 접근하고 있을때 하나의 스레드가 변수값을 수정하였지만 다른 스레드들이 바뀐 변수값을 인지하는 못하는 문제를 가시성 이라고 한다.

동시성에서 발생 할 수 있는 문제로 스레드가 변수의 참,거짓 값에 따라 작동하는 메소드를 실행하고 있을때 해당 변수가 참에서 거짓으로 바뀌어도 이것을 인지하는 못하는 경우가 있다.


# 원자성
스레드가 하나의 연산을 진행하고 있을때 다른 스레드가 이 연산의 중간에 개입하는 문제를 말한다.

동시성에서 발생 할 수 있는 문제로 하나의 변수에 값을 다루는 연산이 하나의 스레드에서 실행중에 똑같은 작업을 하는 또 다른 스레드가 중간에 개입하여 실행되면 이것을 원자성이 깨졌다고 한다.

---


# volatile
가시성을 해결할 수 있는 키워드이다.

해당 키워드가 달린 변수는 값을 캐시로부터 읽어오는것이 아닌 실제 메모리에서 값을 읽어온다.

우선 기본적으로 스레드는 스레드 본인이 변수에 대해 쓰기 작업을 하지 않는다면 캐싱된 변수값을 불러온다.

```java
private static boolean stop = false;
public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
    	for(int i = 0; !stop; i++) {
        }
        System.out.println("done")
    }).start();
    Thread.sleep(1000);
    stop = true;
}
```
위 코드는 새로운 스레드가 stop이 true로 바뀌기 전까지 계속 반복되는 코드이다.

**Thread.sleep(1000)**을 통해 1초 후 stop 변수를 true로 바꾸고 있다.

즉 기대값은 1초 뒤 **"done"**이라는 출력값이 나와야 하는데 실제로는 그렇지 않다.

**main스레드**에서 stop을 true로 바꿔도 **thread입장**에서는 캐싱된 stop변수값을 읽어오기 때문에

바뀐 stop을 인지하지 못하는 가시성 문제가 발생한다.

위 문제를 해결하는 방법은 두 가지가 있다.

#### 1. 스레드 본인이 직접 변수를 수정
스레드 본인이 직접 변수를 수정한다면 실제 메모리에 쓰기 작업을 함과 동시에 캐싱도 업데이트한다.

#### 2. volatile 키워드 사용
volatile 키워드 사용 시 스레드들이 해당 변수를 읽어올때 캐싱된 값이 아닌 실제 메모리에 저장된 값을 읽어온다.

```java
private static volatile boolean stop = false;
public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(() -> {
    	for(int i = 0; !stop; i++) {
        }
        System.out.println("done")
    }).start();
    Thread.sleep(1000);
    stop = true;
}
```

위의 경우 main스레드가 stop을 true로 바꾸게 되면 thread는 바뀐 실제 stop값인 true를 읽어와서 프로그램이 종료되게 된다.

하지만 volatile은 가시성만 지켜주고 원자성은 해결하지 못한다.
읽기 작업만 하는 경우 volatile키워드가 유용하겠으나 쓰기 작업과 동시에 일어난다면 값의 연산에 동시성 문제는 여전히 발생한다.

추가적으로 참조 타입에서의 volatile은 거의 사용을 하지 않는다.
`(참조값 자체는 가시성이 지켜지지만 참조타입의 경우 내부의 값들은 가시성이 지켜지지 않기 때문)`

---

# synchronized

가시성과 원자성을 해결 할 수 있는 키워드로 동기화를 시켜주는 키워드이다.

**메소드 형식**과 **블록 형식**의 사용방법이 있다.

**메소드 형식**
```java
public synchronized void method() {}
```
해당 메소드에 대해 하나의 스레드가 사용중일 경우 다른 스레드는 해당 메소드를 사용 할 수 없다.

---

**블록 형식**
```java
public void method() {
	synchronized(object) {}
}
```
메소드는 여러 스레드가 동시에 사용 할 수 있지만 synchronized 블록안 내부 자원은 하나의 스레드만 접근 할 수 있다.


## 문제 상황

다음은 동시성이 발생한 상황으로 원자성이 지켜지지 않은 상황이다.


```java
private static int a = 0;

public static void main(String[] args) {
	method();
    method();
    
    Thread.sleep(1000);
    
    System.out.println(a);
}

public static void method() {
	Thread thread = new Thread(() -> {
    	for(int i = 0; i < 1000; i++) {
        	a++;
		}
	}).start();
}
```

2개의 **method**호출에 의해 각각 스레드가 생성된다.
총 두개의 스레드가 동시에 a 변수에 접근하여 연산을 진행하면서 기대값 2000과는 다른
예상하지 못하는 결과값을 출력한다.

이때 **a++**이라는 연산은 다음과 같은 연산 과정을 거친다.

1. **READ**
   a라는 변수를 실제 메모리로부터 읽어오거나 CPU 캐시에 해당 변수의 값이 있다면 캐시로 부터 읽어온다.
2. **CALC**
   1번을 통해 읽어온 변수값을 CPU 레지스터에서 연산을 진행한다.
3. **WRITE**
   2번을 통해 연산된 결과값을 다시 변수의 실제 메모리와 캐시에 저장한다.

서로 다른 두개의 스레드가 변수 a의 초기값 0을 각각 읽어서

각각 1이라는 값을 실제 메모리에 덮어씌우게 되면서 하나의 스레드 연산이 덮어씌어지게 된다.

**a++**이라는 연산과정이 원자성이 지켜지지 않았다.

위 상황은 synchronized를 통해 해결하고자 한다면

```java
public static void method() {
    Thread thread = new Thread(() -> {
        for(int i = 0; i < 1000; i++) {
            synchronized (object) {
                a++;
            }
        }
    }).start();
}
```

하지만 동기화는 스레드들이 해당 블록이나 메소드에서 지연 현상을 겪게 되면서 성능적인 부분에서 떨어진다.

---


# Atomic

자바에서 지원하는 원자성을 지켜주는 클래스로
AtomicInteger, AtomicLong 등 여러 타입이 존재한다.

원자성을 지켜주는 방식은 하나의 연산 중 다른 스레드의 개입을 막는다기 보다는 개입이 있다면 다시 연산을 하는 방식이다.

이를 **CAS**라 부른다.

## CAS
Compare-And-Sweap으로

스레드가 연산 후,

변수의 연산 전의 값과 실제 변수가 저장된 메모리의 값과 비교하여 비슷하다면 **true**
연산 전의 값과 실제 변수가 저장된 메모리의 값이 다르다면 **false**

true라면 연산 결과값을 그대로 실제 메모리에 반영하고
false라면 읽기부터 다시 돌아가 위 과정을 반복한다.

```java
private static AtomicInteger a = new AtomicInteger(0);
    public static void main(String[] args) throws InterruptedException {
        method();
        method();
        Thread.sleep(10000);
        System.out.println(a);
    }

    public static void method() {
        Thread thread = new Thread(() -> {
            for(int i = 0; i < 10000; i++) {
                a.incrementAndGet();
            }
        });
        thread.start();
    }
```

원자성을 지키면서 동기화를 하지 않기 때문에 synchronized보다 덜 부담스럽다.

하지만 Atomic의 단점도 존재하는데

1. 많은 스레드가 동시에 연산을 진행하는 경우 CAS과정이 빈번하게 반복되어 성능이 떨어질 수 있다.
2. **ABA문제**로 A->B->A순으로 변수값이 변경되면 CAS과정에서 통과된다. `(중간 변경 과정을 무시)`

2번 문제는 **AtomicStampedReference**을 사용하여 버전을 통해 관리를 하면 된다.



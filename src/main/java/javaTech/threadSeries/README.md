# Runnable, Callable

---
두 개의 인터페이스는 모두 `@FunctionalInterface` 어노테이션을 사용하여 

하나의 추상 메소드(각각 run, call)만 정의되어 있어 람다식으로 사용 가능하다.

또한 둘 모두 스레드와 관련된 인자로 많이 사용한다.

---

둘 모두 람다함수로 스레드와 관련된 함수이다.

## Runnable
리턴이 없는 람다식으로 **Thread**의 생성자 함수로 사용할 수 있다.

```java
public static void main(String[] args) {
    Thread thread = new Thread(() -> System.out.println("Runnable 람다식"));
    thread.start();
}
```

위 처럼 리턴이 없는 Thread에서 사용한다.
Runnable의 특징으로 상위 블록에게 에러를 전달 할 수 없다.

```java
public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("리턴이 없는 runnable 람다식");
            throw new IllegalArgumentException("에러를 던집니다.");
        };

        Thread thread = new Thread(task);
        try {
            thread.start();
        } catch (Exception e) {
            System.out.println("에러 감지");
            throw new RuntimeException(e);
        }
    }
```
위 코드를 실행하면 thread가 에러를 던지고 종료되면서 콘솔에 기록되지만,

상위의 try-catch에서는 감지하지 못한다.

스레드를 실행할 때 thread.start()는 새로운 스레드를 만들어서 비동기로 실행하기 때문에 예외가 발생해도 main 스레드에서 잡을 수 없다.

추가적으로 Thread는 새로운 스레드를 생성하여 작동한다.

새롭게 생성시킨 스레드는 인자로 받는 Runnable인자를 실행시키고 재사용하지 못하고 제거된다.

무분별한 스레드 생성은 성능이슈를 발생시킨다.

---

## Callable
리턴 값이 존재하는 람다식으로 **ExecutorService**와 함께 주로 사용한다.

새로운 스레드를 생성하는 **Thread**에 비해 **ExecutorService**는 정하는 수만큼 스레드풀을 생성하여
해당 스레드풀에 Callable인자를 넘겨 처리하고 **ExecutorService**로 정의된 스레드풀에서의 스레드역시 재사용 가능하다.

생성한 **ExecutorService** 스레드풀에 **Callable인자**를 넘기면 `Future<T>` 형식의 데이터로 받을 수 있다.

```java
public static void main(String[] args) {
    Callable<String> callable = () -> "문자열입니다.";
    
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Future<String> future = executorService.submit(callable);
    try {
        System.out.println(future.get());
    } catch (ExecutionException | InterruptedException e) {
        throw new RuntimeException(e);
    } finally {
        executorService.shutdown();
    }
}
```
**Future**의 **get**은 try-catch문법으로 예외처리를 추가로 진행해야 한다.
Thread와 달리 **ExecutorService**는 **Future객체**에 담아서 던지기 때문에 상위레벨에서 에러를 감지할 수 있다.

또한 invokeAll, invokeAny와 같은 메소드를 통해 여러 callable인자를 넘기고 처리할 수 있다.

Thread와는 달리 작업이 끝나도 스레드가 실행상태를 유지하기 때문에 마지막에 **shutdown()**을 호출해야 한다.




둘 모두 람다함수로 스레드와 관련된 함수이다.


## Runnable
리턴이 없는 람다식으로 **Thread**의 생성자 함수로 사용할 수 있다.

```java
public static void main(String[] args) {
    Thread thread = new Thread(() -> System.out.println("Runnable 람다식"));
    thread.start();
}
```

위 처럼 리턴이 없는 Thread에서 사용한다.
Runnable의 특징으로 상위 블록에게 에러를 전달 할 수 없다.

```java
public static void main(String[] args) {
        Runnable task = () -> {
            System.out.println("리턴이 없는 runnable 람다식");
            throw new IllegalArgumentException("에러를 던집니다.");
        };

        Thread thread = new Thread(task);
        try {
            thread.start();
        } catch (Exception e) {
            System.out.println("에러 감지");
            throw new RuntimeException(e);
        }
    }
```
위 코드를 실행하면 thread가 에러를 던지고 종료되면서 콘솔에 기록되지만, 

상위의 try-catch에서는 감지하지 못한다.

스레드를 실행할 때 thread.start()는 새로운 스레드를 만들어서 비동기로 실행하기 때문에 예외가 발생해도 main 스레드에서 잡을 수 없다.

추가적으로 Thread는 새로운 스레드를 생성하여 작동한다.

새롭게 생성시킨 스레드는 인자로 받는 Runnable인자를 실행시키고 재사용하지 못하고 제거된다.

무분별한 스레드 생성은 성능이슈를 발생시킨다.

---

## Callable
리턴 값이 존재하는 람다식으로 **ExecutorService**와 함께 주로 사용한다.

새로운 스레드를 생성하는 **Thread**에 비해 **ExecutorService**는 정하는 수만큼 스레드풀을 생성하여
해당 스레드풀에 Callable인자를 넘겨 처리하고 **ExecutorService**로 정의된 스레드풀에서의 스레드역시 재사용 가능하다.

생성한 **ExecutorService** 스레드풀에 **Callable인자**를 넘기면 `Future<T>` 형식의 데이터로 받을 수 있다.

```java
public static void main(String[] args) {
    Callable<String> callable = () -> "문자열입니다.";
    
    ExecutorService executorService = Executors.newFixedThreadPool(3);
    Future<String> future = executorService.submit(callable);
    try {
        System.out.println(future.get());
    } catch (ExecutionException | InterruptedException e) {
        throw new RuntimeException(e);
    } finally {
        executorService.shutdown();
    }
}
```
**Future**의 **get**은 try-catch문법으로 예외처리를 추가로 진행해야 한다.
Thread와 달리 **ExecutorService**는 **Future객체**에 담아서 던지기 때문에 상위레벨에서 에러를 감지할 수 있다.

또한 invokeAll, invokeAny와 같은 메소드를 통해 여러 callable인자를 넘기고 처리할 수 있다.

Thread와는 달리 작업이 끝나도 스레드가 실행상태를 유지하기 때문에 마지막에 **shutdown()**을 호출해야 한다.

---

###  추가적으로 Thread를 사용할때 Thread를 직접 상속하는 방법과 Runnable 인터페이스를 구현하여 사용하는 방법의 차이

**Thread 직접 상속**

Thread를 직접 상속하면 중복 상속이 불가능한 자바환경에서 다른 클래스를 상속받지 못한다.

해당 Thread를 상속한 클래스는 Thread의 역할을 수행해야 한다.

즉 해당 클래스는 Thread의 구체적인 로직을 해당 클래스 내부에서 직접 정의해야 한다.

Thread와 강하게 결합하면서 유연한 설계가 힘들다.

**스레드의 역할 + 작업 내용 정의**

---

**Runnable 인터페이스 구현**

Runnable 인터페이스를 구현하면 다른 클래스도 같이 상속하여 구현할 수 있다.

해당 클래스는 Thread와 결합도가 낮아 유연한 설계가 가능하다.

**작업 내용을 정의하는 인터페이스 + 이외의 작업 내용 정의**



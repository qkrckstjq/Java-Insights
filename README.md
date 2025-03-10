# Java-Insights
자바의 여러 스킬들 혹은 개념들에 대해 기술합니다.


목차<br><br>

### 1️⃣ Java 기본 개념 & 문법<br>

- Primitive Type과 Reference Type의 차이점<br>
- == 연산자와 .equals() 메서드의 차이<br>
- String, StringBuilder, StringBuffer의 차이<br>
- static 키워드의 의미와 활용 예제<br>
- final, finally, finalize의 차이<br> 
- instanceof 연산자의 사용과 단점<br>
- try-with-resources 구문의 사용 목적과 필요성<br>
- Autoboxing과 Unboxing의 개념 및 성능 이슈<br>
- Java에서 pass-by-value와 pass-by-reference 개념<br>
- Java에서 다형성의 개념<br><br>

### 2️⃣ 객체지향 프로그래밍 (OOP)<br>

- 객체지향의 4대 특징(캡슐화, 상속, 다형성, 추상화)<br>
- abstract class와 interface의 차이<br>
- 상속과 구성(Composition)의 차이<br>
- SOLID 원칙의 개념과 적용 경험<br>
- 오버로딩(Overloading)과 오버라이딩(Overriding)의 차이<br>
- super와 this 키워드의 사용법<br>
- equals()와 hashCode()를 함께 오버라이딩하는 이유<br>
- Cloneable 인터페이스와 clone() 메서드의 동작 원리<br>
- deep copy와 shallow copy의 차이<br>
- Java 8에서 default method가 추가된 이유<br><br>

### 3️⃣ Java 컬렉션 프레임워크<br>
- ArrayList와 LinkedList의 차이와 사용 시기<br>
- HashMap, TreeMap, LinkedHashMap의 차이<br>
- HashSet과 TreeSet의 차이<br>
- ConcurrentHashMap과 Hashtable의 차이<br>
- PriorityQueue와 일반 Queue의 차이<br>
- WeakHashMap의 개념과 사용 시기<br>
- HashMap에서 equals()와 hashCode()를 재정의하지 않았을 때 문제점<br>
- Collections.synchronizedList()와 CopyOnWriteArrayList의 차이<br><br>

### 4️⃣ Java 예외 처리<br>
- Checked Exception과 Unchecked Exception의 차이<br>
- try-catch-finally 블록에서 finally가 실행되지 않는 경우<br>
- throw와 throws 키워드의 차이<br>
- 예외를 catch한 후 rethrow하는 경우<br>
- try-with-resources를 사용하는 이유<br>
- Error 클래스와 Exception 클래스의 차이<br>
- OutOfMemoryError 발생의 주요 원인<br>
- 커스텀 예외(Custom Exception)의 생성 방법과 사용 예시<br><br>

### 5️⃣ Java 멀티스레딩 & 동시성<br>
- Thread 클래스와 Runnable 인터페이스의 차이<br>
- synchronized 키워드의 동작 방식과 한계<br>
- volatile 키워드의 사용 시기<br>
- ReentrantLock과 synchronized의 차이<br>
- ThreadLocal의 개념과 사용 시기<br>
- ExecutorService와 ForkJoinPool의 차이<br>
- Future와 CompletableFuture의 차이<br>
- Callable과 Runnable의 차이<br>
- Deadlock 발생 원인과 해결 방법<br>
- Race Condition의 개념과 방지법<br>
- AtomicInteger가 필요한 이유<br><br>

### 6️⃣ Java 메모리 관리 & GC(Garbage Collection)<br>
- Java 메모리 구조(Heap, Stack, Metaspace)<br>
- Java에서 객체가 GC 대상이 되는 조건<br>
- Young Generation, Old Generation, Metaspace의 개념<br>
- Serial GC, Parallel GC, G1 GC, ZGC의 차이<br>
- SoftReference, WeakReference, PhantomReference의 차이<br>
- OOM(OutOfMemoryError)이 발생하는 주요 원인<br>
- GC Logs 분석과 튜닝 방법<br><br>

### 7️⃣ Java 8+ 기능<br>
- Java 8의 Stream API 활용법과 장점<br>
- map()과 flatMap()의 차이<br>
- Optional 클래스의 장점과 활용법<br>
- Predicate, Function, Consumer, Supplier의 차이<br>
- default method가 추가된 이유<br>
- Collectors.groupingBy() 활용 방법<br>
- Lambda와 Method Reference의 차이<br><br>

### 8️⃣ Java & Spring 관련<br>
- Java Reflection의 개념과 사용 시기<br>
- ClassLoader의 역할과 동작 방식<br>
- Java 애플리케이션에서 Proxy 사용 이유<br>
- Java에서 동적 프록시(Dynamic Proxy) 생성 방법<br>
- Spring Bean의 라이프사이클<br>
- Spring DI(Dependency Injection)의 원리<br>
- AOP(Aspect-Oriented Programming)의 핵심 개념<br>
- JPA의 영속성 컨텍스트 개념<br>
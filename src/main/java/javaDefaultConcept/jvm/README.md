# JVM의 구성 요소 및 구조

---

JVM은 Java Virtual Machine으로 자바 언어의 장점중 하나인 다양한 OS환경에서 
자바 바이트코드를 운영체제에 맞게 해석하고 실행을 하게 하는 가상 머신이다.

## 구성 요소
JVM은 다음과 같은 요소로 구성되어있다.

### Class Loader
Class Loader는 javac가 .class로 만든 클래스파일을 JVM으로 로드하고 링크 및 초기화 작업을 수행합니다.

단계
1. 클래스 로딩

- 클래스 파일을 읽어 메모리에 로드()

2. 링크(Linking)
- .class 파일의 바이트코드를 JVM이 실행할 수 있게 준비하는 과정
- **검증** : 바이트 코드 유효성 검사 (보안 확인)
- **준비** : static 변수 메모리 할당 및 기본값으로 초기화
- **해석** : 심볼릭 레퍼런스를 실제 메모리 주소로 변환

3. 초기화(initialization)
- static 변수 초기화 및 static 블록 실행.

### Runtime Data Area
JVM이 프로그램을 실행하는 동안 사용하는 메모리 영역

>**Method Area** <br>
> 클래스 메타데이터, static 변수, 상수 풀, default 메소드 저장<br>
>**Heap Area** <br>
>new 키워드로 생성되는 인스턴스 저장 <br>
>**Stack Area** <br>
>각 스레드 고유 공간으로 메소드 프레임, 지역 변수 저장 <br>
>**PC Register** <br>
>현재 실행 중인 명령의 주소를 저장, 스레드별 독립 <br>
>**Native Method Stack** <br>
>네이티브 메소드(C, C++) 호출 시 사용되는 스택

### Execution Engine
JVM의 핵심 컴포넌트로, 바이트코드(.class파일)를 기계어로 변환하여 실행합니다.

**Interpreter** : 바이트코드를 한 줄씩 해석, 빠른 실행 가능, 속도 느림

**JIT Compiler** : 빈번히 사용되는 코드를 기계어로 변환 및 캐싱

**Garbage Collector** : 힙 영역의 참조되지 않는 객체를 정리

### Native Method Interface (네이티브 메서드 인터페이스)
자바 코드에서 C, C++ 같은 네이티브 코드를 호출할 수 있도록 돕는 인터페이스

### Native Method Library
네이티브 메서드의 구현 라이브러리.

---

.java 파일을 실행하는 과정은 다음과 같다.

1. javac를 통해 .java 파일을 바이트코드(.class파일)로 변환
2. JVM의 클래스로더가 바이트코드를 읽으면서 코드를 읽을 준비를 합니다.
3. JVM의 Execution Engine이 바이트코드를 기계어로 번역하면서 한줄씩 실행시킵니다.
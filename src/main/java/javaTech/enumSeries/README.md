# enum

---

enum은 논리적으로 비슷한 상수들의 집합이다.

class키워드 대신 enum키워드를 통해 사용할 수 있고, 컴파일과정에서 Enum 클래스를 상속받는것으로 컴파일된다.

enum의 메타데이터등은 메소드 메모리 영역에 할당된다.

enum의 각 상수값들은 하나의 객체 인스턴스들로 힙 영역에 할당된다.

상수값들은 public static final 성질을 가지고 컴파일 된다.

enum으로 선언된 값들은 모두 싱글톤 패턴으로 메모리에 등록된다.

__각각 어떤 상황에서 enum과 final이 적합할까?__

### `final`  한 두개의 간단한 상수값을 다룰때 적합
### `enum`  개수가 많고 단순 상수값이 아닌 여러 성질을 동시에 가지는 상수 집합이 필요할때

변하지 않는 상수값으로 사용하게 될 경우 static과 같이 사용하여 변수의 불변성을 더하는 키워드이다.

단일 상수로 사용한다면 static final이 더 적합하나 **비슷한 성질을 지닌 상수 집합**에서 적합하지 않은 이유는 다음과 같다.

서버를 운영한다 했을 경우 요청에 대한 응답 코드들의 집합을 한번 final로 작성해보면
```java
public class StatusListClass {
    public static final StatusListClass OK = new StatusListClass(200, "Success");
    public static final StatusListClass NOT_FOUND = new StatusListClass(404, "Not Found");
    public static final StatusListClass SERVER_ERROR = new StatusListClass(500, "Server Error");

    private static final Map<Integer, String> statusList = new HashMap<>(Map.of(
            OK.code, OK.message,
            NOT_FOUND.code, NOT_FOUND.message,
            SERVER_ERROR.code, SERVER_ERROR.message
    ));

    private final int code;
    private final String message;

    private StatusListClass(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(int statusCode) {
        if(statusList.containsKey(statusCode)) {
            return statusList.get(statusCode);
        }
        throw new IllegalArgumentException("존재 하지 않는 응답 코드입니다.");
    }
}
```

생성자 함수를 private로 걸어 외부에서 인스턴스 생성을 못하게 막고,
각 요소를 해쉬맵에 등록하여 더 빠르게 요소를 찾게 했다.

하지만 요소가 10개 이상, 혹은 그 이상으로 요소가 많아진다면 각 요소를 직접 수동으로 해쉬맵에 집어넣어줘야 한다.
반대로 enum으로 구현한다면

```java
public enum StatusListEnum {
    OK(200, "Success"),
    BAD_REQUEST(404, "Not Found"),
    SERVER_ERROR(500, "Server Error");

    private static final Map<Integer, String> statusList = new HashMap<>();

    static {
        for (StatusListEnum status : StatusListEnum.values()) {
            statusList.put(status.statusCode, status.message);
        }
    }

    private final int statusCode;
    private final String message;

    StatusListEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }
    
    public static String getMessage(int statusCode) {
        if(statusList.containsKey(statusCode)) {
            return statusList.get(statusCode);
        }
        throw new IllegalArgumentException("존재 하지 않는 응답 코드입니다.");
    }
}
```

전체적인 구성과 각 요소가 저장되는 방법은 동일하지만 enum으로 선언하면 value()라는 메소드를 이용하여
구성 요소들을 순회할 수 있다.

enum은 switch사용이 가능하다.

```java
StatusListEnum status = StatusListEnum.OK;

switch (status) {
    case OK -> System.out.println("Success");
    case BAD_REQUEST -> System.out.println("Bad Request");
    case SERVER_ERROR -> System.out.println("Server Error");
}
```
반대로 class의 경우
```java
StatusListClass status = StatusListClass.OK;

switch (status) { 
    case StatusListClass.OK -> System.out.println("Success");
    case StatusListClass.NOT_FOUND -> System.out.println("Not Found");
    case StatusListClass.SERVER_ERROR -> System.out.println("Server Error");
}
```
위 같은 코드는 불가능하다.


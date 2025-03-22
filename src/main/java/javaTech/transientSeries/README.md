# transient 키워드

**transient** 키워드는 변수 앞에 적을 수 있는 키워드로

해당 키워드가 붙은 변수는 Serializable인터페이스를 구현한 클래스(Jackson의 ObjectMapper는 Serialize인터페이스 구현)가 직렬화를 시도할때 직렬화의 대상이 되지 않습니다.

```java
public class Person {
    public transient String name;
    public int age;
    public Map<Integer, String> childrens;

    public Person(String name, int age, Map<Integer, String> childrens) {
        this.name = name;
        this.age = age;
        this.childrens = childrens;
    }
}

private static final ObjectMapper objectMapper = new ObjectMapper();
public static void main(String[] args) {
    Person me = new Person("김진섭", 45, new HashMap<>(Map.of(0, "장싱싱", 1, " 김감긴")));
    try {
        String serial = objectMapper.writeValueAsString(me);
        System.out.println(serial);
        //{"age":45,"childrens":{"0":"장싱싱","1":" 김감긴"}}
    } catch (JsonProcessingException e) {
        throw new RuntimeException(e);
    }

}
```
위 코드에서 **sout**의 결과값에서 **name**은 포함되지 않는다.

반대로 transient로 특정 필드값을 직렬화에서 무시하고 역직렬화를 시도하면
해당 값은 기본값 혹은 null값으로 넘어가게 된다.

추가적으로 **Jackson**으로 직렬화를 시도할때 변수에 **@JsonProperty** 어노테이션을 붙이면
해당 변수의 **transient키워드를 무시하고** 직렬화 한다.
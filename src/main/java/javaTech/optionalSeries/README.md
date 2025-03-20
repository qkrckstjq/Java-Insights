# Optional

---

Optional은 자바 8 이후에 추가된 기술이다.

사용 목적은 NPE를 회피하기 위한 목적이 있다.

Optional이 기존에는 불가능한 예외처리를 가능하게 해주는 기능은 아니지만 명시적으로 해당 객체가 null 유무를 표현할 수 있다.

```java
    private static final Map<String, Person> people = new HashMap<>(Map.of(
            "박찬섭", new Person("박찬섭", 25),
            "qkrckstjq", new Person("qkrckstjq", 25)));
    public static void main(String[] args) {

        Optional<Person> optionalPerson = Optional.ofNullable(people.get("qwe"));
        Person person1 = optionalPerson.orElse(new Person("기본 유저", 0));

        System.out.println(person1.name);

        Person person2 = findByNameWithOutOptional("박찬섭");
        Person person3 = findByNameWithOptional("qkrckstjq");

        System.out.println(person2.name);
        System.out.println(person3.name);
    }

    public static Person findByNameWithOutOptional(String name) {
        Person person = people.get(name);
        if(person == null) {
            throw new NullPointerException("존재하지 않는 유저입니다.");
        }
        return person;
    }

    public static Person findByNameWithOptional(String name) {
        return Optional.of(people.get(name))
                .orElseThrow(() -> new NullPointerException("존재하지 않는 유저입니다."));
    }
```

**Optional.of** : 해당 객체는 개발자가 NULL값이 오지 않는다는 것을 명시적으로 확인할 수 있는 코드이다.

**Optional.ofNullable** : 해당 객체는 NULL값이 올 수 있다는 것을 명시적으로 표현한 코드이다.

논리적으로 첫번째 메소드는 null값이 리턴되는 경우는 없다.

따라서 첫번째 메소드와 동일한 작업을 하는 경우 Optional.of를 사용한다.
만약 Optional.of에 null이 들어간다면 NPE가 발생한다.

Optional클래스로 감싸진 객체는 isPresent()같은 메소드를 사용하여 값이 존재하는지 확인 할 수 도있다.

---

Optional은 꼭 좋은건 아니다.

Optional클래스는 래퍼 클래스로 빈번한 사용은 추가적인 메모리 공간을 할애한다.

클래스의 필드값으로 선언하면 직/역렬화 과정에서 문제가 발생한다.

또한 List, Map, Set 같은 자료구조를 Optional로 감싸는건 좋지 않다.

위 자료구조는 단순히 isEmpty나 길이 0으로 대체할 수 있기 때문이다.

---

# 정리

1. Optional은 NPE가 발생할때 실수를 줄이기 위해 사용한다.
2. Optional.of()는 NULL값이 오면 NPE예외를 던지기 때문에 주의해서 사용해야 한다.
3. 클래스 필드값, List, Set, Map같은 자료구조에는 사용하지 않는다.
4. 래퍼 클래스로 메모리를 추가로 사용하기 때문에 모든 상황에서 적합한것은 아니다.

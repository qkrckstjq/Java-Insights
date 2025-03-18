# Stream API

---

Stream API는 자바 8버전 이후에 추가된 기능이다.

일반 반복문`for`과 달리 Collection 내부에서 처리한다.

기존 반복문은 for문안에서 리스트 요소에 접근`(리스트의 값에 접근하여 꺼내는것)`을 작성하는 개발자의 코드에 의존한다. `(외부 반복자)`

반대로 StreamAPI를 사용하면 Collection 내부에서 처리된다 `(내부 반복자)`

Collection 내부에서 처리된다는 뜻은 리스트의 내부 요소에 개발자가 **어떻게 접근**할지를 정의할 필요가 없이 **어떻게 처리**하면 되는지만 다루면 된다.

Collection -> Stream() 메소드 제공, Stream 인터페이스는 map(), filter()등 여러 메소드 제공

Stream 특성 중 중간 처리와 최종 처리가 있다.

중간 처리는 **매핑, 필터링**과 같은 데이터를 가공하는 부분이고 최종 처리는 가공된 데이터를 **집계**하는 부분이다.

최종 처리는 필수 요소이며, 최종 처리를 만나기 전까지 중간 연산은 지연이 된다.

또한 스트림은 파이프라인을 통한 처리를 하는데

하나의 요소가 하나의 중간 연산을 통과하면 대기하는것이 아니라 바로 다음 중간 연산으로 흘러간다.
말 그대로 스트림(흐르는 물)같은 흐름으로 외부 반복자는 하나의 중간 처리 과정을 거치면 다른 중간 처리까지 기다리는 반면에
스트림은 필요한 데이터만을 바로 다음 중간 연산으로 넘긴다.

하지만 `sorted()`와 같은 특별한 중간 연산은 논리적으로 요소들을 기다렸다 처리하는 중간 연산자도 있다.

또한 Stream은 parallel 연산을 지원한다.(멀티 스레드)

---

# 비교
정해진 ArrayList에 대해 각각의 방법으로 필터링을 걸었을때의 비교이다.

```java
package JavaCollectionsFramework.streamApi;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return this.name;
    }

    public int getAge() {
        return this.age;
    }
}
List<Person> nameList = new ArrayList<>(List.of(
        new Person("박찬섭", 25),
        new Person("김잡이", 22),
        new Person("신나은", 15),
        new Person("최정규", 56),
        new Person("정승기", 36),
        new Person("박찬비", 27)
        ));
```
위 리스트에서 25살 이상 "박"으로 시작하는 인물 리스트 사전순으로 정리하기

## 외부 반복자
```java
public static List<Person> method1(List<Person> nameList) {
    List<Person> result = new ArrayList<>();
    for(Person person : nameList) {
        if (person.getAge() >= 25 && person.getName().startsWith("박")) {
            result.add(person);
        }
    }
    result.sort(Comparator.comparing(Person::getName));
    return result;
}
```
## 내부 반복자 Stream 이용
```java
public static List<Person> method2(List<Person> nameList) {
        return nameList.stream()
                .filter((person) -> person.getAge() >= 25 && person.getName().startsWith("박"))
                .sorted(Comparator.comparing(Person::getName))
                .toList()
                
                ;
    }
```
두 코드의 차이는 외부 반복자는 중간 리스트를 생성하는 부분, 그 외 논리적으로 큰 차이는 없다.

하지만 Stream을 활용한 연산이 코드도 간결하고 가독성도 좋다.

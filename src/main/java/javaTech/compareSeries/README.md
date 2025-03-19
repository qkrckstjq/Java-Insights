# Comparable, Comparator

---

둘 모두 주로 정렬에 사용되는 비교값을 정하는 방법으로

List, PQ, TreeMap, TreeSet 등에서 비교값을 정할때 사용 할 수 있다.

# Comparable

Comparable 인터페이스를 구현한 클래스는 CompareTo 메소드를 오버라이딩하여
List형식의 배열을 정렬할 수 있다.

```java
public class Person implements Comparable<Person>{
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

    @Override
    public int compareTo(Person other) {
        return Integer.compare(this.age, other.age);
    }
}
```

해당 Person이라는 클래스의 객체에서 age를 기준으로 정렬하고자 할때
compareTo를 오버라이딩하여 위 처럼 선언하면 

외부에서 List<Person>의 변수를 다음과 같이 사용하여 정렬 할 수 있다.

```java
  public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("박찬섭", 30));
        people.add(new Person("김향기", 25));
        people.add(new Person("나선지", 35));

        Collections.sort(people);
    }
```
이 comparable은 하나의 기준으로만 정렬 할 수 있다.

여러 기준으로 우선순위를 두고 정렬 할 수 없다. 또한 compareTo에서 정해놓은 방법이외에 다른 기준으로도 정렬 할 수 없다.

---

# Comparator

Comparator는 객체 외부에서 해당 객체를 어떻게 정렬할 것인지 정할 수 있는 방법으로

Comparator가 함수형 인터페이스(@FunctionalInterface)이기 때문에 람다식(() -> {})으로 사용할 수 있다.

Comparable에 비해 여러 기준을 삼을 수도 있다.

```java
public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("박찬섭", 30));
        people.add(new Person("김향기", 25));
        people.add(new Person("나선지", 35));
        
        people.sort(Comparator.comparing(Person::getAge));
    }
```
위는 단순히 나이만으로 정렬했지만

```java
import java.util.Comparator;

Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
people.sort(ageComparator.thenComparing(nameComparator));
```
위 처럼 나이순을 우선순위로, 이후 이름 사전순으로 정렬을 진행 할 수 있다.

| | Comparable | Comparator |
| - |------------|------------|
| 기본 정렬 가능 | O          | O          |
| 여러 기준 정렬 가능 | X          | O          |
| 클래스 내부 수정 필요 | O          | X          |
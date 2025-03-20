package javaTech.optionalSeries;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class Main {
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
}

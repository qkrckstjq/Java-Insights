package javaTech.compareSeries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Person> people = new ArrayList<>();
        people.add(new Person("박찬섭", 30));
        people.add(new Person("김향기", 25));
        people.add(new Person("나선지", 35));

//        Collections.sort(people);
        Comparator<Person> ageComparator = Comparator.comparing(Person::getAge);
        Comparator<Person> nameComparator = Comparator.comparing(Person::getName);
//        people.sort(Comparator.comparing(Person::getAge));
//        people.sort(ageComparator.reversed());
        people.sort(ageComparator.thenComparing(nameComparator));
        printList(people);
    }

    public static void printList(List<Person> list) {
        list.forEach((person) -> System.out.println(person.getName() + person.getAge()));
    }
}


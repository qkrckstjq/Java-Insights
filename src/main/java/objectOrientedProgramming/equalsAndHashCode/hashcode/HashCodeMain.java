package objectOrientedProgramming.equalsAndHashCode.hashcode;

import objectOrientedProgramming.equalsAndHashCode.Person;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

public class HashCodeMain {
    public static void main(String[] args) {
        Set<Person> set = new HashSet<>();
        Person p1 = new Person("hi");
        Person p2 = new Person("hi");

        set.add(p1);
        if (set.contains(p2)) {
            System.out.println("set안에 p2 있음");
        } else {
            System.out.println("set안에 p2 없음");
        }
    }
}

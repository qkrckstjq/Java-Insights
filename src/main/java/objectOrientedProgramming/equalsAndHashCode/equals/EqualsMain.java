package objectOrientedProgramming.equalsAndHashCode.equals;

import objectOrientedProgramming.equalsAndHashCode.Person;

import java.util.Objects;

public class EqualsMain {
    public static void main(String[] args) {
        Person p1 = new Person("hi");
        Person p2 = new Person("hi");

        if (p1 == p2) {
            System.out.println("p1과 p2는 서로 같다.");
        } else {
            System.out.println("p1과 p2는 서로 다르다.");
        }

        if (p1.equals(p2)) {
            System.out.println("p1과 p2는 서로 같다.");
        } else {
            System.out.println("p1과 p2는 서로 다르다.");
        }
    }
}

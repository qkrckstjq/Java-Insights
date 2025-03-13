package objectOrientedProgramming.clone;

public class Main {
    public static void main(String[] args) {
        Person p1 = new Person("p1");
        Person p2 = (Person) p1.clone();

        System.out.println(p1 == p2);

        p2.addChild("안녕");

        p1.printChildrens();
        System.out.println();
        p2.printChildrens();
    }
}

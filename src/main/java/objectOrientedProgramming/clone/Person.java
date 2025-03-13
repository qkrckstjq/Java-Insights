package objectOrientedProgramming.clone;

import java.util.ArrayList;
import java.util.List;

public class Person implements Cloneable{
    private final String name;
    private final List<String> childrens = new ArrayList<>(List.of("김", "이", "박"));

    public Person(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void printChildrens() {
        for (String child : childrens) {
            System.out.println(child);
        }
    }

    public void addChild(String child) {
        this.childrens.add(child);
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}

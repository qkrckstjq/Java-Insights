package javaTech.transientSeries;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

public class Person {
    @JsonProperty
    public transient String name;
    public transient int age;
    public Map<Integer, String> childrens;

    public Person() {}

    public Person(String name, int age, Map<Integer, String> childrens) {
        this.name = name;
        this.age = age;
        this.childrens = childrens;
    }
}

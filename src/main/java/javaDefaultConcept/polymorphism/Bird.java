package javaDefaultConcept.polymorphism;

public abstract class Bird implements Animal{
    String name;

    void fly() {
        System.out.println(name + " can fly");
    }

    abstract void run();

    public Bird(String name) {
        this.name = name;
    }
}

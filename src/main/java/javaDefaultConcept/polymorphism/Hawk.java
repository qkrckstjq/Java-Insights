package javaDefaultConcept.polymorphism;

public class Hawk extends Bird {
    public Hawk(String name) {
        super(name);
    }

    @Override
    void run() {
        System.out.println("hawk can run");
    }

    @Override
    public void sound() {
        System.out.println("매 울음소리");
    }
}

package javaDefaultConcept.polymorphism;

public class Eagle extends Bird {
    public Eagle(String name) {
        super(name);
    }

    @Override
    void run() {
        System.out.println("eagle can run");
    }

    @Override
    public void sound() {
        System.out.println("독수리 울음소리");
    }
}

package JavaDefaultConcept.polymorphism;

public interface Animal {
    static final boolean dieAble = true;
    void sound();

    default void qwe() {
        System.out.println("dlrp ehlspe");
    }
}

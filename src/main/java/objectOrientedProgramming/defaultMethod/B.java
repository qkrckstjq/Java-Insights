package objectOrientedProgramming.defaultMethod;

public interface B {
    default void hello() {
        System.out.println("hi");
    }
}

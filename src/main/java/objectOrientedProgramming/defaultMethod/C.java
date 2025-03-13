package objectOrientedProgramming.defaultMethod;

public class C implements A, B{
    @Override
    public void hello() {
        A.super.hello();
        System.out.println("hi, hello");
    }
}

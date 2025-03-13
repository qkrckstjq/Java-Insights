package objectOrientedProgramming.superThis;

public class Child extends Parents{
    public Child(String name) {
        super(name);
    }

    public void superName() {
        System.out.println(super.name);
    }

    public void superPrintName() {
        super.printName();
    }
}

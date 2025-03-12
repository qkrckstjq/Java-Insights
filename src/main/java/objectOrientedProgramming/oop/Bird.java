package objectOrientedProgramming.oop;

public abstract class Bird implements Animal{
    private String name;

    public Bird(String name) {
        this.name = name;
    }

    public void fly() {
        System.out.println(this.name + " can fly");
    }
}

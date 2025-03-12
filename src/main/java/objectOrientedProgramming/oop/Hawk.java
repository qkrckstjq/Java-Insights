package objectOrientedProgramming.oop;

public class Hawk extends Bird{

    public Hawk(String name) {
        super(name);
    }

    @Override
    public void sound() {
        System.out.println("매 울음소리");
    }
}

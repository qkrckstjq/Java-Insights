package JavaDefaultConcept.polymorphism;

public class Polymorphism {
    public static void main(String[] args) {
        Bird hawk = new Hawk("매");
        Bird eagle = new Eagle("독수리");

        hawk.fly();
        eagle.fly();

        hawk.sound();
        eagle.sound();
    }
}

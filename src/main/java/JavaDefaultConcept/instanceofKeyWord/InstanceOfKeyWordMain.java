package JavaDefaultConcept.instanceofKeyWord;

public class InstanceOfKeyWordMain {
    public static void main(String[] args) {
        Animal dog = new Dog();
        Animal cat = new Cat();

        SoundInstanceOf(dog);
        SoundInstanceOf(cat);

        SoundInterface(dog);
        SoundInterface(cat);
    }

    private static void SoundInstanceOf(Animal animal) {
        if (animal instanceof Dog) {
            System.out.println("멍멍");
        } else if (animal instanceof Cat) {
            System.out.println("냐옹");
        }
    }

    private static void SoundInterface(Animal animal) {
        animal.sound();
    }
}

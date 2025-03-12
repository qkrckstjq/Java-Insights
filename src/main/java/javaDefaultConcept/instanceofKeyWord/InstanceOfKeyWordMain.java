package javaDefaultConcept.instanceofKeyWord;

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
        if (animal instanceof Dog dog) {
            dog.sound();
        } else if (animal instanceof Cat cat) {
            cat.sound();
        }
    }

    private static void SoundInterface(Animal animal) {
        animal.sound();
    }
}

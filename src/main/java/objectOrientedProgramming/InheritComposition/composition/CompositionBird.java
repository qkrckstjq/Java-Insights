package objectOrientedProgramming.InheritComposition.composition;

import objectOrientedProgramming.InheritComposition.Animal;

public class CompositionBird {
    public Animal animal = new Animal();

    public void eat() {
        animal.eat();
    }
}

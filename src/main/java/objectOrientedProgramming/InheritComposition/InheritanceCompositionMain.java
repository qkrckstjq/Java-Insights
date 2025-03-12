package objectOrientedProgramming.InheritComposition;

import objectOrientedProgramming.InheritComposition.composition.CompositionBird;
import objectOrientedProgramming.InheritComposition.inheritance.InheritanceBird;

public class InheritanceCompositionMain {
    public static void main(String[] args) {
        InheritanceBird inheritanceBird = new InheritanceBird();
        CompositionBird compositionBird = new CompositionBird();

        inheritanceBird.eat();
        compositionBird.eat();
    }
}

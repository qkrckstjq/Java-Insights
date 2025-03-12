package objectOrientedProgramming.InheritComposition;

import objectOrientedProgramming.InheritComposition.composition.CompositionBird;
import objectOrientedProgramming.InheritComposition.inheritance.InheritanceBird;

import java.io.IOException;
import java.util.Stack;

public class InheritanceCompositionMain {
    public static void main(String[] args) {
        InheritanceBird inheritanceBird = new InheritanceBird();
        CompositionBird compositionBird = new CompositionBird();

        inheritanceBird.eat();
        compositionBird.eat();

        Stack<Integer> stack = new Stack<>();
        stack.push(1);
        stack.add(1);
    }
}

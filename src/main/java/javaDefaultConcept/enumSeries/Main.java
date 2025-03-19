package javaDefaultConcept.enumSeries;

public class Main {
    public static void main(String[] args) {
        System.out.println(StatusListEnum.OK.getMessage());
        System.out.println(StatusListEnum.getMessage(500));

        System.out.println(StatusListClass.OK.getMessage());
        System.out.println(StatusListClass.getMessage(500));
    }
}

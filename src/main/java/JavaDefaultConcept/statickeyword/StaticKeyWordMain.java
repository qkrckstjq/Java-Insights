package JavaDefaultConcept.statickeyword;

public class StaticKeyWordMain {
    public static void main(String[] args) {
        privateStaticMethod();
        //privateMethod(); 오류
    }

    private static void privateStaticMethod() {
        System.out.println("this is private static method");
    }

    private void privateMethod() {
        System.out.println("this is private method");
    }
}

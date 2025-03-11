package JavaDefaultConcept.passByValue;

public class PassByValue {
    public static void main(String[] args) {
        int a = 5;
        System.out.println("원시 타입");
        System.out.println("메소드에 기입 전");
        System.out.println(a);
        changePrimitiveValue(a);
        System.out.println("메소드에 기입 후");
        System.out.println(a);
        System.out.println("==============");

        StringBuilder b = new StringBuilder("b");
        System.out.println("참조 타입");
        System.out.println("메소드에 기입 전");
        System.out.println(b);
        changeReferenceValue(b);
        System.out.println("메소드에 기입 후");
        System.out.println(b);
        System.out.println("==============");
    }

    public static void changePrimitiveValue(int num) {
        num = 10;
    }

    public static void changeReferenceValue(StringBuilder str) {
        str.append("c");
        str = new StringBuilder("zxc");
    }
}

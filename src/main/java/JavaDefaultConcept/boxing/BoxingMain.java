package JavaDefaultConcept.boxing;

public class BoxingMain {
    public static void main(String[] args) {
        //AutoBoxing
        Integer a = 5; //자동으로 Integer a = new Integer(5);

        //UnBoxing
        int b = a;     //자동으로 int b = Integer.valueOf(a);
    }
}

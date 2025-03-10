package JavaDefaultConcept.primitiveReference;

public class PrimitiveReferenceMain {
    public static void main(String[] args) {
        int a = 10;
        int b = 10;
        comparison(a, b);

        String c = "test";
        String d = "test";
        comparison(c, d);

        String e = new String("test");
        String f = new String("test");
        comparison(e, f);

    }

    protected static <T> void comparison (T a, T b) {
        if(a == b) {
            System.out.println(a + " 와 " + b + "는 같다");
        } else {
            System.out.println(a + " 와 " + b + "는 다르다");
        }
    }
}

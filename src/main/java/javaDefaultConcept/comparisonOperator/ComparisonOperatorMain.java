package javaDefaultConcept.comparisonOperator;

public class ComparisonOperatorMain {
    public static void main(String[] args) {
        String a = new String("test");
        String b = new String("test");
        Integer c = 1234;
        Integer d = 1234;

        twoComparison(a, b);
        twoComparison(c, d);
    }

    protected static void twoComparison(Object a, Object b) {
        System.out.println("== result");
        if (a == b) {
            System.out.println("same");
        } else {
            System.out.println("diff");
        }

        System.out.println("equals result");
        if (a.equals(b)) {
            System.out.println("same");
        } else {
            System.out.println("diff\n");
        }
    }
}

package JavaDefaultConcept.AutoUnBox;

public class BoxingMain {
    public static void main(String[] args) {
        //AutoBoxing
        Integer a = 5; //자동으로 Integer a = new Integer(5);

        //UnBoxing
        int b = a;     //자동으로 int b = Integer.valueOf(a);

        for (int i = 1; i < 1000; i++) { //매 연산마다 새로운 객체를 생성
            a += i;
        }
    }
}

package JavaDefaultConcept.finalSeries;

public class FinalSeriesMain {

    public static void main(String[] args) {
        System.out.println(tryCatchFinally());
    }

    private static int tryCatchFinally() {
        try {
            System.out.println("this is try");
            return 1;
        } catch (Exception e) {
            System.out.println("this is catch");
            return 2;
        } finally {
            System.out.println("this is finally");
//            return 3;
        }
    }

    public class FinalKeyWord {
        private final int instanceVar; //인스턴스 변수, 인스턴스 생성시 마다 원하는 값으로 생성 후 변경 불가
        private static final int instanceStaticVar = 10; //static으로 선언하여 컴파일 과정에서 해당 값 고정 후 변경 불가

        public FinalKeyWord(int instanceVar) {
            this.instanceVar = instanceVar;
        }

        private void method() {
            final int localVar = 10; //지역 변수 해당 메소드 안에서만 사용하고 변경 할 수 없음
        }
    }
}


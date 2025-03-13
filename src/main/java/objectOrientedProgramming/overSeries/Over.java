package objectOrientedProgramming.overSeries;

public class Over extends Base{

    public Over() {}

    public Over(String str) {
        System.out.println(str);
    }

    public Over(int a, int b) {
        System.out.println(a + b);
    }

    public Over(String str, int a) {
        System.out.println(str + a);
    }

    @Override
    public void baseMethod() {
        System.out.println("덮어 씌우기");
    }
}

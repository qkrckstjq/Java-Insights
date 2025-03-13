package objectOrientedProgramming.overSeries;

public class OverSeriesMain {
    public static void main(String[] args) {
        Over normalOver = new Over();
        Over strOver = new Over("hello");
        Over intOver = new Over(1, 2);
        Over strIntOver = new Over("hello", 1);

        normalOver.baseMethod();
    }
}

package JavaDefaultConcept.instanceofKeyWord;

import java.util.ArrayList;
import java.util.List;

public class InstanceOfKeyWordMain {
    public static void main(String[] args) {
        List<Object> objectList = new ArrayList<>();

        String objectA = new String("test");
        Integer objectB = 10;

        objectList.add(objectA);
        objectList.add(objectB);

        for(Object object : objectList) {
            if (object instanceof String string) {
                System.out.println(string);
            }
        }
    }
}

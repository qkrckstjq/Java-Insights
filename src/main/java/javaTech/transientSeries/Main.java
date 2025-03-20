package javaTech.transientSeries;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.HashMap;
import java.util.Map;

public class Main {
    private static final ObjectMapper objectMapper = new ObjectMapper();
    public static void main(String[] args) {
        Person me = new Person("김진섭", 45, new HashMap<>(Map.of(0, "장싱싱", 1, " 김감긴")));
        try {
            String serial = objectMapper.writeValueAsString(me);
            System.out.println(serial);
            Person deSerial = objectMapper.readValue(serial, Person.class);
            System.out.println(deSerial.name);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }
}

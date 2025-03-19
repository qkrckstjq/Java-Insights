package javaDefaultConcept.enumSeries;

import java.util.HashMap;
import java.util.Map;

public class StatusListClass {
    public static final StatusListClass OK = new StatusListClass(200, "Success");
    public static final StatusListClass NOT_FOUND = new StatusListClass(404, "Not Found");
    public static final StatusListClass SERVER_ERROR = new StatusListClass(500, "Server Error");

    private static final Map<Integer, String> statusList = new HashMap<>(Map.of(
            OK.code, OK.message,
            NOT_FOUND.code, NOT_FOUND.message,
            SERVER_ERROR.code, SERVER_ERROR.message
    ));

    private final int code;
    private final String message;

    private StatusListClass(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public static String getMessage(int statusCode) {
        if(statusList.containsKey(statusCode)) {
            return statusList.get(statusCode);
        }
        throw new IllegalArgumentException("존재 하지 않는 응답 코드입니다.");
    }
}

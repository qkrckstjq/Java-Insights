package javaTech.enumSeries;

import java.util.HashMap;
import java.util.Map;

public enum StatusListEnum {
    OK(200, "Success"),
    BAD_REQUEST(404, "Not Found"),
    SERVER_ERROR(500, "Server Error");

    private static final Map<Integer, String> statusList = new HashMap<>();

    static {
        for (StatusListEnum status : StatusListEnum.values()) {
            statusList.put(status.statusCode, status.message);
        }
    }

    private final int statusCode;
    private final String message;

    StatusListEnum(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public String getMessage() {
        return this.message;
    }

    public static String getMessage(int statusCode) {
        if(statusList.containsKey(statusCode)) {
            return statusList.get(statusCode);
        }
        throw new IllegalArgumentException("존재 하지 않는 응답 코드입니다.");
    }
}

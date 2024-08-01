package site.dadangsinhhoc.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@Builder
public class ResponseObject {
    private final Integer code;
    private final String message;
    private final transient Object data;

    public static ResponseObject error(Integer code, String message) {
        return new ResponseObject(code, message, null);
    }

    public static ResponseObject success(String message, Object data) {
        return new ResponseObject(200, message, data);
    }

    public static ResponseObject success(Object data) {
        return new ResponseObject(200, "SUCCESS", data);
    }
}

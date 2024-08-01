package site.dadangsinhhoc.dto.response;

public record ResponseObject(Integer code, String message, Object data) {
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

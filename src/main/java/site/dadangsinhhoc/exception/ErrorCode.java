package site.dadangsinhhoc.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    BAD_REQUEST(400, "Bad Request"),
    NOT_FOUND(404, "Resource Not Found"),
    CONFLICT(409, "Resource Already Exists"),
    INTERNAL_SERVER_ERROR(500, "Internal Server Error");

    private final int code;
    private final String message;

}
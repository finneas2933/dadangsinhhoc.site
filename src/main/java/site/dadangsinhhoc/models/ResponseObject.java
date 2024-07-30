package site.dadangsinhhoc.models;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ResponseObject {
    private String status;
    private String message;
    private Object data;

    public ResponseObject() {
    }

    public ResponseObject(String status, String message, Object data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

}

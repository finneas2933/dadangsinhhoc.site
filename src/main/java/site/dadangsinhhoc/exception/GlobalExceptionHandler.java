package site.dadangsinhhoc.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import site.dadangsinhhoc.models.ResponseObject;

@ControllerAdvice
public class GlobalExceptionHandler {

//    @ExceptionHandler(Exception.class)
//    public ResponseEntity<ResponseObject> handleResponseObject(ResponseObject ex) {
//        return ResponseEntity.status(ex.getCode()).body(ex);
//    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseObject> handleGenericException(Exception ex) {
        ResponseObject responseObject = ResponseObject.error(
                ErrorCode.INTERNAL_SERVER_ERROR.getCode(),
                "An unexpected error occurred"
        );
        return ResponseEntity.status(responseObject.getCode()).body(responseObject);
    }
}
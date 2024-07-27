package site.dadangsinhhoc.exception;

// Xử lý ngoại lệ tồn tại bản ghi
public class ResourceAlreadyExistsException extends Exception {
    public ResourceAlreadyExistsException() {
    }

    public ResourceAlreadyExistsException(String msg) {
        super(msg);
    }
}
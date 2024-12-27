package vn.test.hub.core.exception;


import org.springframework.http.HttpStatus;

public class NotFoundException extends BaseException {
    public NotFoundException(String message, int code) {
        super(message, code, HttpStatus.NOT_FOUND);
    }

    public NotFoundException(String message) {
        super(message, 404, HttpStatus.NOT_FOUND);
    }
}
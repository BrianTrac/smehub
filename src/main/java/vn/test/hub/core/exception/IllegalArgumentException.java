package vn.test.hub.core.exception;

import org.springframework.http.HttpStatus;

public class IllegalArgumentException extends BaseException {
    public IllegalArgumentException(String message, int code) {
        super(message, code, HttpStatus.BAD_REQUEST);
    }

    public IllegalArgumentException(String message) {
        super(message, 400, HttpStatus.BAD_REQUEST);
    }
}

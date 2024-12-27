package vn.test.hub.core.exception;

import org.springframework.http.HttpStatus;

public class ConflictException extends BaseException {
    public ConflictException(String message, int code) {
        super(message, code, HttpStatus.CONFLICT);
    }

    public ConflictException(String message) {
        super(message, 409, HttpStatus.CONFLICT);
    }
}

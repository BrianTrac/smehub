package vn.test.hub.core.exception;

import org.springframework.http.HttpStatus;

public class BadRequestException extends BaseException {
    public BadRequestException(String message, int code) {
        super(message, code, HttpStatus.BAD_REQUEST);
    }

    public BadRequestException(String message) {
        super(message, 400, HttpStatus.BAD_REQUEST);
    }
}

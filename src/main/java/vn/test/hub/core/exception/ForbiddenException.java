package vn.test.hub.core.exception;

import org.springframework.http.HttpStatus;

public class ForbiddenException extends BaseException {
    public ForbiddenException(String message, int code) {
        super(message, code, HttpStatus.FORBIDDEN);
    }

    public ForbiddenException(String message) {
        super(message, 403, HttpStatus.FORBIDDEN);
    }
}

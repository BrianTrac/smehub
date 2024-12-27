package vn.test.hub.core.exception;

import org.springframework.http.HttpStatus;

public class UnauthorizedException extends BaseException {
    public UnauthorizedException(String message, int code) {
        super(message, code, HttpStatus.UNAUTHORIZED);
    }

    public UnauthorizedException(String message) {
        super(message, 401, HttpStatus.UNAUTHORIZED);
    }
}

package vn.test.hub.core.exception;

import org.springframework.http.HttpStatus;

public class TooManyRequestsException extends BaseException {
    public TooManyRequestsException(String message, int code) {
        super(message, code, HttpStatus.TOO_MANY_REQUESTS);
    }

    public TooManyRequestsException(String message) {
        super(message, 429, HttpStatus.TOO_MANY_REQUESTS);
    }
}


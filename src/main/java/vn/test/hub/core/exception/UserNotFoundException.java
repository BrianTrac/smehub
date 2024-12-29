package vn.test.hub.core.exception;

public class UserNotFoundException extends NotFoundException {
    public UserNotFoundException(String message, int code) {
        super(message, code);
    }

    public UserNotFoundException(String message) {
        super(message, 404001);
    }
}

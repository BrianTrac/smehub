package vn.test.hub.core.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ValidationException extends BaseException {
    private final List<ValidationError> errors;

    @Getter
    @AllArgsConstructor
    public static class ValidationError {
        private String field;
        private String message;
    }

    public ValidationException(String message) {
        super("Validation failed", 422, HttpStatus.UNPROCESSABLE_ENTITY);
        this.errors = new ArrayList<>();
    }

    public ValidationException(String message, List<ValidationError> errors) {
        super(message, 422, HttpStatus.UNPROCESSABLE_ENTITY);
        this.errors = errors;
    }

    public static ValidationException fromBindingResult(BindingResult bindingResult) {
        List<ValidationError> errors = bindingResult.getFieldErrors()
                .stream()
                .map(error -> new ValidationError(error.getField(), error.getDefaultMessage()))
                .toList();
        return new ValidationException("Validation failed", errors);
    }

    @Override
    public Map<String, Object> getMetadata() {
        Map<String, Object> metadata = super.getMetadata();
        metadata.put("validation_errors", errors);
        return metadata;
    }
}

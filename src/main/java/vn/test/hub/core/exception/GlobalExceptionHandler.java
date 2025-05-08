package vn.test.hub.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.test.hub.core.response.BaseResponse;
import vn.test.hub.core.utils.ResponseUtils;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<BaseResponse<Void, Map<String, Object>>> handleBaseException(BaseException ex, HttpServletRequest request) {
        // Create metadata
        Map<String, Object> metadata = ex.getMetadata();
        metadata.put("url", request.getMethod() + " " + request.getRequestURL().toString());

        return ResponseUtils.error(ex.getMessage(), ex.getMetadata(), ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void, Map<String, Object>>> handleGenericException(Exception ex, HttpServletRequest  request) {
        // Create metadata
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("timestamp", Instant.now());
        metadata.put("url", request.getMethod() + " " +  request.getRequestURL().toString());

        return ResponseUtils.error(
                ex.getMessage() != null ? ex.getMessage() : "Internal server error",
                metadata,
                HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

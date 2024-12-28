package vn.test.hub.core.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import vn.test.hub.core.response.BaseResponse;

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

        BaseResponse<Void, Map<String, Object>> response = BaseResponse.<Void, Map<String, Object>> builder()
                .status("error")
                .message(ex.getMessage())
                .metadata(metadata)
                .build();

        return new ResponseEntity<>(response, ex.getHttpStatus());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponse<Void, Map<String, Object>>> handleGenericException(Exception ex, HttpServletRequest  request) {
        // Create metadata
        Map<String, Object> metadata = new HashMap<>();
        metadata.put("timestamp", Instant.now());
        metadata.put("url", request.getMethod() + " " +  request.getRequestURL().toString());

        BaseResponse<Void, Map<String, Object>> response = BaseResponse.<Void, Map<String, Object>>builder()
                .status("error")
                .message(ex.getMessage() != null ? ex.getMessage() : "Internal Server Error")
                .metadata(metadata)
                .build();

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}

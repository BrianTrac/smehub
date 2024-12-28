package vn.test.hub.core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.test.hub.core.response.BaseResponse;

@Component
public class ResponseUtils {

    public <T1, T2> ResponseEntity<BaseResponse<T1, T2>> generateSuccessResponse(String message, T1 data, T2 metadata) {
        return ResponseEntity.ok(
                BaseResponse.<T1, T2>builder()
                        .status("success")
                        .message(message)
                        .data(data)
                        .metadata(metadata)
                        .build()
        );
    }

    public <T1> ResponseEntity<BaseResponse<T1, Void>> generateSuccessResponse(String message, T1 data) {
        return generateSuccessResponse(message, data, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> generateSuccessResponse(String message) {
        return generateSuccessResponse(message, null, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> generateSuccessResponse() {
        return generateSuccessResponse("Success");
    }

    public <T1, T2> ResponseEntity<BaseResponse<T1, T2>> generateCreatedResponse(String message, T1 data, T2 metadata) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<T1, T2>builder()
                        .status("success")
                        .message(message)
                        .data(data)
                        .metadata(metadata)
                        .build()
        );
    }

    public <T1> ResponseEntity<BaseResponse<T1, Void>> generateCreatedResponse(String message, T1 data) {
        return generateCreatedResponse(message, data, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> generateCreatedResponse(String message) {
        return generateCreatedResponse(message, null, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> generateCreatedResponse() {
        return generateCreatedResponse("Created");
    }

    public <T> ResponseEntity<BaseResponse<Void, T>> generateErrorResponse(String message, T errorInfo, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(
                BaseResponse.<Void, T>builder()
                        .status("error")
                        .message(message)
                        .metadata(errorInfo)
                        .build()
        );
    }

    public ResponseEntity<BaseResponse<Void, Void>> generateErrorResponse(String message, HttpStatus httpStatus) {
        return generateErrorResponse(message, null, httpStatus);
    }

    public ResponseEntity<BaseResponse<Void, Void>> generateErrorResponse(String message) {
        return generateErrorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

}

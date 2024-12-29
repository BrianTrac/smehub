package vn.test.hub.core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.test.hub.core.response.BaseResponse;

@Component
public class ResponseUtils {
    // success
    public <T1, T2> ResponseEntity<BaseResponse<T1, T2>> success(String message, T1 data, T2 metadata) {
        return ResponseEntity.ok(
                BaseResponse.<T1, T2>builder()
                        .status("success")
                        .message(message)
                        .data(data)
                        .metadata(metadata)
                        .build()
        );
    }

    public <T1> ResponseEntity<BaseResponse<T1, Void>> success(String message, T1 data) {
        return success(message, data, null);
    }

    public <T1> ResponseEntity<BaseResponse<T1, Void>> success(T1 data) {
        return success("Success", data, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> success(String message) {
        return success(message, null, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> success() {
        return success("Success");
    }

    public <T1, T2> BaseResponse<T1, T2> successResponse(String message, T1 data, T2 metadata) {
        return BaseResponse.<T1, T2>builder()
                .status("success")
                .message(message)
                .data(data)
                .metadata(metadata)
                .build();
    }

    public <T1> BaseResponse<T1, Void> successResponse(String message, T1 data) {
        return successResponse(message, data, null);
    }

    public <T1> BaseResponse<T1, Void> successResponse(T1 data) {
        return successResponse("Success", data, null);
    }

    public BaseResponse<Void, Void> successResponse(String message) {
        return successResponse(message, null, null);
    }

    public BaseResponse<Void, Void> successResponse() {
        return successResponse("Success");
    }


    // created
    public <T1, T2> ResponseEntity<BaseResponse<T1, T2>> created(String message, T1 data, T2 metadata) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<T1, T2>builder()
                        .status("success")
                        .message(message)
                        .data(data)
                        .metadata(metadata)
                        .build()
        );
    }

    public <T1> ResponseEntity<BaseResponse<T1, Void>> created(String message, T1 data) {
        return created(message, data, null);
    }

    public <T1> ResponseEntity<BaseResponse<T1, Void>> created(T1 data) {
        return created("Created", data, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> created(String message) {
        return created(message, null, null);
    }

    public ResponseEntity<BaseResponse<Void, Void>> created() {
        return created("Created");
    }

    public <T1, T2> BaseResponse<T1, T2> createdResponse(String message, T1 data, T2 metadata) {
        return BaseResponse.<T1, T2>builder()
                .status("success")
                .message(message)
                .data(data)
                .metadata(metadata)
                .build();
    }

    public <T1> BaseResponse<T1, Void> createdResponse(String message, T1 data) {
        return createdResponse(message, data, null);
    }

    public <T1> BaseResponse<T1, Void> createdResponse(T1 data) {
        return createdResponse("Created", data, null);
    }

    public BaseResponse<Void, Void> createdResponse(String message) {
        return createdResponse(message, null, null);
    }

    public BaseResponse<Void, Void> createdResponse() {
        return createdResponse("Created");
    }

    
    // error
    public <T> ResponseEntity<BaseResponse<Void, T>> error(String message, T errorInfo, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(
                BaseResponse.<Void, T>builder()
                        .status("error")
                        .message(message)
                        .metadata(errorInfo)
                        .build()
        );
    }

    public ResponseEntity<BaseResponse<Void, Void>> error(String message, HttpStatus httpStatus) {
        return error(message, null, httpStatus);
    }

    public <T> ResponseEntity<BaseResponse<Void, T>> error(String message, T errorInfo) {
        return error(message, errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public <T> ResponseEntity<BaseResponse<Void, T>> error(T errorInfo) {
        return error("Error", errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<BaseResponse<Void, Void>> error(String message) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public ResponseEntity<BaseResponse<Void, Void>> error() {
        return error("Error");
    }

    public <T> BaseResponse<Void, T> errorResponse(String message, T errorInfo, HttpStatus httpStatus) {
        return BaseResponse.<Void, T>builder()
                .status("error")
                .message(message)
                .metadata(errorInfo)
                .build();
    }

    public BaseResponse<Void, Void> errorResponse(String message, HttpStatus httpStatus) {
        return errorResponse(message, null, httpStatus);
    }

    public <T> BaseResponse<Void, T> errorResponse(String message, T errorInfo) {
        return errorResponse(message, errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public <T> BaseResponse<Void, T> errorResponse(T errorInfo) {
        return errorResponse("Error", errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BaseResponse<Void, Void> errorResponse(String message) {
        return errorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public BaseResponse<Void, Void> errorResponse() {
        return errorResponse("Error");
    }
}

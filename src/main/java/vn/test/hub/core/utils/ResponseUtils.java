package vn.test.hub.core.utils;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import vn.test.hub.core.response.BaseResponse;
import vn.test.hub.core.response.ResponseStatus;

@Component
public class ResponseUtils {
    // success
    public static <T1, T2> ResponseEntity<BaseResponse<T1, T2>> success(String message, T1 data, T2 metadata) {
        return ResponseEntity.ok(
                BaseResponse.<T1, T2>builder()
                        .status(ResponseStatus.SUCCESS.getValue())
                        .message(message)
                        .data(data)
                        .metadata(metadata)
                        .build()
        );
    }

    public static <T1> ResponseEntity<BaseResponse<T1, Void>> success(String message, T1 data) {
        return success(message, data, null);
    }

    public static  <T1> ResponseEntity<BaseResponse<T1, Void>> success(T1 data) {
        return success(ResponseStatus.SUCCESS.getValue(), data, null);
    }

    public static ResponseEntity<BaseResponse<Void, Void>> success(String message) {
        return success(message, null, null);
    }

    public static ResponseEntity<BaseResponse<Void, Void>> success() {
        return success(ResponseStatus.SUCCESS.getValue());
    }

    public static <T1, T2> ResponseEntity<BaseResponse<T1, T2>> success(T1 data, T2 metadata) {
        return success(ResponseStatus.SUCCESS.getValue(), data, metadata);
    }

    public static <T1, T2> BaseResponse<T1, T2> successResponse(T1 data, T2 metadata) {
        return BaseResponse.<T1, T2>builder()
                .status(ResponseStatus.SUCCESS.getValue())
                .data(data)
                .metadata(metadata)
                .build();
    }

    public static <T1, T2> BaseResponse<T1, T2> successResponse(String message, T1 data, T2 metadata) {
        return BaseResponse.<T1, T2>builder()
                .status(ResponseStatus.SUCCESS.getValue())
                .message(message)
                .data(data)
                .metadata(metadata)
                .build();
    }

    public static <T1> BaseResponse<T1, Void> successResponse(String message, T1 data) {
        return successResponse(message, data, null);
    }

    public static <T1> BaseResponse<T1, Void> successResponse(T1 data) {
        return successResponse(ResponseStatus.SUCCESS.getValue(), data, null);
    }

    public static BaseResponse<Void, Void> successResponse(String message) {
        return successResponse(message, null, null);
    }

    public static BaseResponse<Void, Void> successResponse() {
        return successResponse(ResponseStatus.SUCCESS.getValue());
    }


    // created
    public static <T1, T2> ResponseEntity<BaseResponse<T1, T2>> created(String message, T1 data, T2 metadata) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                BaseResponse.<T1, T2>builder()
                        .status(ResponseStatus.CREATED.getValue())
                        .message(message)
                        .data(data)
                        .metadata(metadata)
                        .build()
        );
    }

    public static <T1> ResponseEntity<BaseResponse<T1, Void>> created(String message, T1 data) {
        return created(message, data, null);
    }

    public static <T1> ResponseEntity<BaseResponse<T1, Void>> created(T1 data) {
        return created(ResponseStatus.CREATED.getValue(), data, null);
    }

    public static ResponseEntity<BaseResponse<Void, Void>> created(String message) {
        return created(message, null, null);
    }

    public static ResponseEntity<BaseResponse<Void, Void>> created() {
        return created(ResponseStatus.CREATED.getValue());
    }

    public static <T1, T2> BaseResponse<T1, T2> createdResponse(String message, T1 data, T2 metadata) {
        return BaseResponse.<T1, T2>builder()
                .status(ResponseStatus.CREATED.getValue())
                .message(message)
                .data(data)
                .metadata(metadata)
                .build();
    }

    public static <T1> BaseResponse<T1, Void> createdResponse(String message, T1 data) {
        return createdResponse(message, data, null);
    }

    public static <T1> BaseResponse<T1, Void> createdResponse(T1 data) {
        return createdResponse(ResponseStatus.CREATED.getValue(), data, null);
    }

    public static BaseResponse<Void, Void> createdResponse(String message) {
        return createdResponse(message, null, null);
    }

    public static BaseResponse<Void, Void> createdResponse() {
        return createdResponse(ResponseStatus.CREATED.getValue());
    }

    
    // error
    public static <T> ResponseEntity<BaseResponse<Void, T>> error(String message, T errorInfo, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(
                BaseResponse.<Void, T>builder()
                        .status(ResponseStatus.ERROR.getValue())
                        .message(message)
                        .metadata(errorInfo)
                        .build()
        );
    }

    public static <T, T2> ResponseEntity<BaseResponse<T, T2>> error(String message, HttpStatus httpStatus) {
        return ResponseEntity.status(httpStatus).body(
                BaseResponse.<T, T2>builder()
                        .status(ResponseStatus.ERROR.getValue())
                        .message(message)
                        .data(null)
                        .metadata(null)
                        .build()
        );
    }

    public static <T> ResponseEntity<BaseResponse<Void, T>> error(String message, T errorInfo) {
        return error(message, errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<BaseResponse<Void, T>> error(T errorInfo) {
        return error(ResponseStatus.ERROR.getValue(), errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> ResponseEntity<BaseResponse<T, Void>> error(String message) {
        return error(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static ResponseEntity<BaseResponse<Void, Void>> error() {
        return error(ResponseStatus.ERROR.getValue());
    }

    public static <T> BaseResponse<Void, T> errorResponse(String message, T errorInfo, HttpStatus httpStatus) {
        return BaseResponse.<Void, T>builder()
                .status(ResponseStatus.ERROR.getValue())
                .message(message)
                .metadata(errorInfo)
                .build();
    }

    public static BaseResponse<Void, Void> errorResponse(String message, HttpStatus httpStatus) {
        return errorResponse(message, null, httpStatus);
    }

    public static <T> BaseResponse<Void, T> errorResponse(String message, T errorInfo) {
        return errorResponse(message, errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static <T> BaseResponse<Void, T> errorResponse(T errorInfo) {
        return errorResponse(ResponseStatus.ERROR.getValue(), errorInfo, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static BaseResponse<Void, Void> errorResponse(String message) {
        return errorResponse(message, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public static BaseResponse<Void, Void> errorResponse() {
        return errorResponse(ResponseStatus.ERROR.getValue());
    }
}

package vn.test.hub.core.utils;

import vn.test.hub.core.BaseResponse;

public class ResponseUtil {

    public static <T, T1> BaseResponse<T, T1> success (T data, T1 metadata, String message) {
        return BaseResponse.<T, T1>builder()
                .status("success")
                .message(message)
                .data(data)
                .metadata(metadata)
                .build();
    }

    public static <T> BaseResponse<T, Void> success (T data, String message) {
        return success(data, null, message);
    }

    public static <T> BaseResponse<T, Void> success (T data) {
        return success(data, "Success");
    }

    public static <T> BaseResponse<Void, Void> success (String message) {
        return success(null, message);
    }

    public static <T> BaseResponse<Void, Void> success () {
        return success("Success");
    }
}

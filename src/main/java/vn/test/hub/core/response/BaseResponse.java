package vn.test.hub.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Builder
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@JsonPropertyOrder({"status", "message", "data", "metadata"}) // Ensures the order
public class BaseResponse<T, T1> {
    private String status; // success, error
    private String message; // message
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data; // data
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T1 metadata; // metadata contain url, timestamp in BaseException, ...
}

package vn.test.hub.core.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;

import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import vn.test.hub.core.utils.SnakeCaseSerializer;

@Setter
@Getter
@Builder
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class) // Convert to snake case
@JsonPropertyOrder({"status", "message", "metadata", "data"}) // Ensures the order
public class BaseResponse<T, T1> {
    private String status; // success, error

    private String message; // message

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = SnakeCaseSerializer.class)
    private T data; // data

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonSerialize(using = SnakeCaseSerializer.class)
    private T1 metadata; // metadata contain url, timestamp in BaseException, ...
}

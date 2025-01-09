package vn.test.hub.core.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ResponseStatus {
    SUCCESS("success"),
    ERROR("error"),
    CREATED("created");

    private final String value;
}

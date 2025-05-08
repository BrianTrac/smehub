package vn.test.hub.core.searching;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class FilterParam {
    private String field;
    private Operators operator;
    private Object value;
}

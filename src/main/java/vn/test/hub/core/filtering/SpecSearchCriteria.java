package vn.test.hub.core.filtering;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SpecSearchCriteria {
    private String key;
    private SearchOperation operation;
    private Object value;
    private boolean orPredicate;

    public SpecSearchCriteria(String key, SearchOperation operation, Object value) {
        this.key = key;
        this.operation = operation;
        this.value = value;
        this.orPredicate = false;
    }
}

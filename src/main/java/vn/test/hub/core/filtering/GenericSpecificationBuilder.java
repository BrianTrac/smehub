package vn.test.hub.core.filtering;

import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class GenericSpecificationBuilder<T> {
    private final List<SpecSearchCriteria> criteriaList = new ArrayList<>();

    public GenericSpecificationBuilder<T> with(SpecSearchCriteria criteria) {
        if (criteria != null) {
            criteriaList.add(criteria);
        }
        return this;
    }

    public Specification<T> build() {
        if (criteriaList.isEmpty()) {
            return null;
        }

        Specification<T> result = new GenericSpecification<>(criteriaList.get(0));

        for (int i = 1; i < criteriaList.size(); i++) {
            SpecSearchCriteria criteria = criteriaList.get(i);
            Specification<T> spec = new GenericSpecification<>(criteria);

            if (criteria.isOrPredicate()) {
                result = Specification.where(result).or(spec);
            }
            else {
                result = Specification.where(result).and(spec);
            }
        }

        return result;
    }
}

package vn.test.hub.core.utils;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.core.searching.Operators;

import java.util.ArrayList;
import java.util.List;

public class SearchingUtils {

    public static <T>Specification<T> findBy(List<FilterParam> params) {
        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            for (FilterParam param : params) {
                Path<?> path = root.get(param.getField());
                Operators operator = param.getOperator();
                Predicate predicate = operator.buildPredicate(criteriaBuilder, path, param.getValue());
                if (predicate != null) {
                    predicates.add(predicate);
                }
            }

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}

package vn.test.hub.core.utils;

import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import vn.test.hub.core.filtering.GenericSpecificationBuilder;
import vn.test.hub.core.filtering.SearchCriteriaParser;
import vn.test.hub.core.filtering.SpecSearchCriteria;
import vn.test.hub.core.searching.FilterParam;
import vn.test.hub.core.searching.Operators;

import java.util.ArrayList;
import java.util.List;

public class SearchingUtils {

    public static Specification<?> buildSpecification(String search) {
        if (search == null || search.isEmpty()) {
            return null;
        }

        SearchCriteriaParser parser = new SearchCriteriaParser();
        List<SpecSearchCriteria> criteriaList = parser.parse(search);
        if (criteriaList.isEmpty()) {
            return null;
        }

        GenericSpecificationBuilder<?> builder = new GenericSpecificationBuilder<>();
        criteriaList.forEach(builder::with);

        return builder.build();
    }

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

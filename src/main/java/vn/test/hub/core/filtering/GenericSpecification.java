package vn.test.hub.core.filtering;

import jakarta.persistence.criteria.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

@RequiredArgsConstructor
public class GenericSpecification<T> implements Specification<T> {

    private final SpecSearchCriteria criteria;

    @Override
    @SuppressWarnings("unchecked")
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        Object value = criteria.getValue();
        Path<?> path = root.get(criteria.getKey());
        Class<?> fieldType = path.getJavaType();

        return switch (criteria.getOperation()) {
            case EQUALITY:
                yield  cb.equal(path, castToRequiredType(value, fieldType));
            case NEGATION:
                yield cb.notEqual(path, castToRequiredType(value, fieldType));
            case GREATER_THAN:
                yield cb.greaterThan((Path<? extends Comparable>) path, (Comparable) castToRequiredType(value, fieldType));
            case GREATER_THAN_OR_EQUAL:
                yield  cb.greaterThanOrEqualTo((Path<? extends Comparable>) path, (Comparable) castToRequiredType(value, fieldType));
            case LESS_THAN:
                yield cb.lessThan((Path<? extends Comparable>) path, (Comparable) castToRequiredType(value, fieldType));
            case LESS_THAN_OR_EQUAL:
                yield cb.lessThanOrEqualTo((Path<? extends Comparable>) path, (Comparable) castToRequiredType(value, fieldType));
            case IN:
                if (value instanceof List<?>) {
                    yield path.in(convertList((List<?>) value, fieldType));
                } else if (value instanceof Collection<?>) {
                    yield path.in((Collection<?>) value);
                }
            case NOT_IN:
                if (value instanceof List<?>) {
                    yield cb.not(path.in(convertList((List<?>) value, fieldType)));
                } else if (value instanceof Collection<?>) {
                    yield cb.not(path.in((Collection<?>) value));
                }
            case BETWEEN:
                assert value instanceof List<?>;
                List<?> list = (List<?>) value;
                if (list.size() == 2) {
                    Comparable lowerBound = (Comparable) castToRequiredType(list.get(0), fieldType);
                    Comparable upperBound = (Comparable) castToRequiredType(list.get(1), fieldType);
                    yield cb.between((Expression<? extends Comparable>) path, lowerBound, upperBound);
                }
                else {
                    throw new IllegalArgumentException("BETWEEN operator requires 2 values");
                }
            case STARTS_WITH:
                yield cb.like((Expression<String>) path, castToRequiredType(value, String.class) + "%");
            case ENDS_WITH:
                yield cb.like((Expression<String>) path, "%" + castToRequiredType(value, String.class));
            case CONTAINS:
                yield cb.like((Expression<String>) path, "%" + castToRequiredType(value, String.class) + "%");
            default:
                throw new UnsupportedOperationException("Operation " + criteria.getOperation() + " is not supported");
        };
    }

    private Object castToRequiredType(Object value, Class<?> targetType) {
        if (value == null) return null;

        if (targetType.isAssignableFrom(value.getClass())) {
            return value;
        }

        String stringValue = value.toString();

        if (targetType == Integer.class) {
            return Integer.valueOf(stringValue);
        } else if (targetType == Long.class) {
            return Long.valueOf(stringValue);
        } else if (targetType == Double.class) {
            return Double.valueOf(stringValue);
        } else if (targetType == Float.class) {
            return Float.valueOf(stringValue);
        } else if (targetType == Boolean.class) {
            return Boolean.valueOf(stringValue);
        } else if (targetType == String.class) {
            return value.toString();
        }
        else if (targetType == LocalDateTime.class) {
            return LocalDateTime.parse(stringValue);
        }
        else {
            throw new IllegalArgumentException("Cannot convert value to " + targetType.getName());
        }
    }

    private List<Object> convertList(List<?> list, Class<?> targetType) {
        return list.stream()
                .map(value -> castToRequiredType(value, targetType))
                .toList();
    }
}
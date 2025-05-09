package vn.test.hub.core.searching;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Path;
import jakarta.persistence.criteria.Predicate;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public enum Operators {
    EQ {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            return cb.equal(path, value);
        }
    },
    NEQ {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            return cb.notEqual(path, value);
        }
    },
    GT {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            try {
                if (Comparable.class.isAssignableFrom(path.getJavaType())) {
                    return cb.greaterThan(path.as(path.getJavaType().asSubclass(Comparable.class)), (Comparable) value);
                } else {
                    log.error("Invalid field type for field {}", path);
                }
            } catch (Exception e) {
                log.error("Invalid value type for field {}", path);
            }
            return null;
        }
    },
    GTE {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            try {
                if (Comparable.class.isAssignableFrom(path.getJavaType())) {
                    return cb.greaterThanOrEqualTo(path.as(path.getJavaType().asSubclass(Comparable.class)), (Comparable) value);
                } else {
                    log.error("Invalid field type for field {}", path);
                }
            } catch (Exception e) {
                log.error("Invalid value type for field {}", path);
            }
            return null;
        }
    },
    LT {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            try {
                if (Comparable.class.isAssignableFrom((path.getJavaType()))) {
                    return cb.lessThan(path.as(path.getJavaType().asSubclass(Comparable.class)), (Comparable) value);
                } else {
                    log.error("Invalid field type for field {}", path);
                }
            } catch (Exception e) {
                log.error("Invalid value type for field {}", path);
            }
            return null;
        }
    },
    LTE {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            try {
                if (Comparable.class.isAssignableFrom(path.getJavaType())) {
                    return cb.lessThanOrEqualTo(path.as(path.getJavaType().asSubclass(Comparable.class)), (Comparable) value);
                } else {
                    log.error("Invalid field type for field {}", path);
                }
            } catch (Exception e) {
                log.error("Invalid value type for field {}", path);
            }
            return null;
        }
    },
    IN {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            return path.in(value);
        }
    },
    NIN {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            return cb.not(path.in(value));
        }
    },
    LIKE {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            return cb.like(path.as(String.class), "%" + value + "%");
        }
    },
    NLIKE {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            return cb.notLike(path.as(String.class), "%" + value + "%");
        }
    },
    BETWEEN {
        @Override
        public Predicate buildPredicate(CriteriaBuilder cb, Path<?> path, Object value) {
            if (value instanceof Object[]) {
                Object[] values = (Object[]) value;
                if (values.length == 2) {
                    return cb.between(path.as(path.getJavaType().asSubclass(Comparable.class)), (Comparable) values[0], (Comparable) values[1]);
                } else {
                    log.error("Invalid value type for field {}", path);
                }
            } else {
                log.error("Invalid value type for field {}", path);
            }
            return null;
        }
    };

    public abstract Predicate buildPredicate(CriteriaBuilder cb, Path<?> Path, Object value);
}

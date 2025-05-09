package vn.test.hub.core.filtering;

import vn.test.hub.core.utils.DateUtils;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SearchCriteriaParser {

    public List<SpecSearchCriteria> parse(String search) {
        List<SpecSearchCriteria> criteriaList = new ArrayList<>();
        if (search == null || search.isBlank()) return criteriaList;

        String[] orParts = search.split("\\|");

        for (String orPart : orParts) {
            boolean isOr = (orParts.length > 1);
            String[] filters = orPart.split(";");

            for (String filter : filters) {
                filter = filter.trim();
                if (filter.isEmpty()) continue;

                Matcher m = Pattern.compile("^(\\w+)(!?[<>=]*)(:|>=|<=|>|<)(.+)$").matcher(filter);
                if (m.find()) {
                    String key = m.group(1);
                    String rawOp = m.group(3);
                    String value = m.group(4).trim();

                    SearchOperation op = mapToOperation(rawOp, value);
                    if (op == null) {
                        throw new IllegalArgumentException("Invalid operation: " + rawOp);
                    }

                    Object parsedValue = parseValue(op, value, key);
                    if (parsedValue == null) {
                        throw new IllegalArgumentException("Invalid value: " + value);
                    }

                    SpecSearchCriteria criteria = new SpecSearchCriteria(key, op, parsedValue);
                    criteria.setOrPredicate(isOr);
                    criteriaList.add(criteria);
                }
            }
        }

        return criteriaList;
    }

    private SearchOperation mapToOperation(String rawOp, String value) {
       switch(rawOp) {
           case ":":
               if (value.startsWith("*") && value.endsWith("*")) {
                   return SearchOperation.CONTAINS;
               } else if (value.startsWith("*")) {
                   return SearchOperation.ENDS_WITH;
               } else if (value.endsWith("*")) {
                   return SearchOperation.STARTS_WITH;
               } else if (value.contains(",")) {
                   return SearchOperation.IN;
               } else if (value.contains("~")) {
                   return SearchOperation.BETWEEN;
               }
               else {
                     return SearchOperation.EQUALITY;
               }

           case "!:":
               if (value.contains(",")) {
                   return SearchOperation.NOT_IN;
               }
                return SearchOperation.NEGATION;

           case ">": return SearchOperation.GREATER_THAN;
           case "<": return SearchOperation.LESS_THAN;
           case ">=": return SearchOperation.GREATER_THAN_OR_EQUAL;
           case "<=": return SearchOperation.LESS_THAN_OR_EQUAL;
           default: return SearchOperation.EQUALITY;
       }
    }

    private Object parseValue(SearchOperation op, String value, String key) {
        boolean isDateField = key.toLowerCase().contains("date") || key.toLowerCase().contains("at");

        if (op == SearchOperation.BETWEEN) {
            String[] parts = value.split("~");
            if (parts.length != 2) {
                throw new IllegalArgumentException("BETWEEN operator requires 2 values");
            }

            if (isDateField) {
                LocalDateTime from = DateUtils.parseToLocalDateTime(parts[0].trim());
                LocalDateTime to = DateUtils.parseToLocalDateTime(parts[1].trim());
                return List.of(from, to);
            }

            return List.of(parts[0].trim(), parts[1].trim());
        }

        if (op == SearchOperation.IN || op == SearchOperation.NOT_IN) {
            return Arrays.stream(value.split(","))
                    .map(String::trim)
                    .toList();
        }

        if (op == SearchOperation.EQUALITY || op == SearchOperation.NEGATION) {
            return value;
        }

        if (op == SearchOperation.GREATER_THAN || op == SearchOperation.GREATER_THAN_OR_EQUAL ||
                 op == SearchOperation.LESS_THAN || op == SearchOperation.LESS_THAN_OR_EQUAL) {
            if (isDateField) {
                return DateUtils.parseToLocalDateTime(value);
            }
            else {
                try {
                    return Integer.parseInt(value);
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }

        return value;
    }
}

package vn.test.hub.core.utils;

import org.springframework.data.domain.Sort;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PaginationUtils {

    public static Map<String, String> filterValidSortColumns(List<String> paramColumns, List<String> validColumns) {
        Map<String, String> sortParam = new HashMap<>();

        for (String param : paramColumns) {
            String[] elements = param.split(",");

            if (validColumns.contains(elements[0])) {
                if (elements.length == 2 && (elements[1].equalsIgnoreCase("asc") || elements[1].equalsIgnoreCase("desc"))) {
                    sortParam.put(elements[0], elements[1]);
                }
                else {
                    sortParam.put(elements[0], "asc");
                }
            }
        }

        return sortParam;
    }

    public static Sort toSort(Map<String, String> sortBy) {
        Sort sort = Sort.unsorted();

        for (Map.Entry<String, String> entry : sortBy.entrySet()) {
            String key = entry.getKey();
            String value = entry.getValue();

            sort = sort.and(Sort.by(Sort.Direction.fromString(value), key));
        }

        return sort;
    }
}

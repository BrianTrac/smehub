package vn.test.hub.core.utils;

import org.springframework.data.domain.Sort;

public class SortingUtils {
    public static Sort convertToSort(String[] sort) {
        Sort sortObj = Sort.unsorted();
        if (sort != null) {
            for (String s : sort) {
                String[] parts = s.split(",");
                if (parts.length == 2) {
                    String property = parts[0];
                    String direction = parts[1].equalsIgnoreCase("desc") ? "desc" : "asc";
                    sortObj = sortObj.and(Sort.by(Sort.Direction.fromString(direction), property));
                }
            }
        }
        return sortObj;
    }
}

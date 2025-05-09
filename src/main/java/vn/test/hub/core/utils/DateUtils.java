package vn.test.hub.core.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

public class DateUtils {

    private static final List<DateTimeFormatter> DATE_FORMATS = List.of(
            DateTimeFormatter.ISO_LOCAL_DATE,
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")
    );

    public static LocalDateTime parseToLocalDateTime(String input) {
        for (DateTimeFormatter formatter : DATE_FORMATS) {
            try {
                if (formatter.toString().contains("HH:mm:ss")) {
                    return LocalDateTime.parse(input, formatter);
                } else {
                    return LocalDate.parse(input, formatter).atStartOfDay();
                }
            } catch (Exception e) {
                // Ignore and try the next format
            }
        }
        throw new IllegalArgumentException("Invalid date format: " + input);
    }

    public static Date toDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}

package edu.hw5;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Optional;
import java.util.regex.Pattern;

public class Task3 {
    private static final DateTimeFormatter FORMAT1 = DateTimeFormatter.ISO_LOCAL_DATE;
    private static final DateTimeFormatter FORMAT2 = DateTimeFormatter.ofPattern("yyyy-M-d");
    private static final DateTimeFormatter FORMAT3 = DateTimeFormatter.ofPattern("d/M/yyyy");
    private static final DateTimeFormatter FORMAT4 = DateTimeFormatter.ofPattern("d/M/yy");

    private static final DateTimeFormatter[] FORMATTERS = new DateTimeFormatter[]
        {FORMAT1, FORMAT2, FORMAT3, FORMAT4};
    private static final String FORMAT5 = "tomorrow";
    private static final String FORMAT6 = "today";
    private static final String FORMAT7 = "yesterday";

    public static Optional<LocalDate> parseDate(String date) {
        return switch (date) {
            case FORMAT5 -> Optional.of(LocalDate.now().plusDays(1));

            case FORMAT6 -> Optional.of(LocalDate.now());

            case FORMAT7 -> Optional.of(LocalDate.now().minusDays(1));

            default -> parseDateTimePattern(date);
        };
    }

    private static Optional<LocalDate> parseDateTimePattern(String date) {
        var pattern = Pattern.compile("(\\d+) day(s)? ago");
        var matcher = pattern.matcher(date);
        if (matcher.find()) {
            return Optional.of(LocalDate.now().minusDays(Integer.parseInt(matcher.group(1))));
        }

        for (var format : FORMATTERS) {
            try {
                return Optional.of(LocalDate.parse(date, format));
            } catch (DateTimeParseException ignored) {
            }
        }

        return Optional.empty();
    }

    private Task3() {
    }
}

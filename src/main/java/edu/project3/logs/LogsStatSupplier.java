package edu.project3.logs;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class LogsStatSupplier {
    public static final Map<Integer, String> STATUS_NAME
        = Map.ofEntries(
        Map.entry(200, "OK"),
        Map.entry(404, "Not Found"),
        Map.entry(500, "Internal Server Error"),
        Map.entry(304, "Not Modified"),
        Map.entry(302, "Found")
    );

    public static long getRequestsAmount(List<LogNGINX> logs) {
        return logs.size();
    }

    public static String getAverageResponseSize(List<LogNGINX> logs) {
        return Math.round(logs.stream()
            .mapToInt(LogNGINX::bytesSent)
            .average()
            .orElse(0))
            + "b";
    }

    public static Map<String, Long> getTheResourcesFrequency(List<LogNGINX> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(
                LogNGINX::resource,
                Collectors.counting()
            ));
    }

    public static Map<Map.Entry<Integer, String>, Long> getResponseCodesCount(List<LogNGINX> logs) {
        return logs.stream()
            .collect(Collectors.groupingBy(
                log -> Map.entry(
                    log.status(),
                    STATUS_NAME.getOrDefault(log.status(), getCodeClass(log.status()))
                ),
                Collectors.counting()
            ));
    }

    public static List<LogNGINX> getFilteredByDateLogs(
        List<LogNGINX> logs,
        @NotNull OffsetDateTime from,
        @NotNull OffsetDateTime to
    ) {
        return logs.stream()
            .filter(log -> log.time().isAfter(from) && log.time().isBefore(to))
            .toList();
    }

    private static String getCodeClass(int code) {
        if (code >= 100 && code <= 199) {
            return "Informational";
        }
        if (code >= 200 && code <= 299) {
            return "Successful";
        }
        if (code >= 300 && code <= 399) {
            return "Redirection";
        }
        if (code >= 400 && code <= 499) {
            return "Client Error";
        }
        if (code >= 500 && code <= 599) {
            return "Server error";
        }
        return "Undefined";
    }

    private LogsStatSupplier() {
    }
}

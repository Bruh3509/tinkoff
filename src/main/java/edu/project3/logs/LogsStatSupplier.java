package edu.project3.logs;

import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.jetbrains.annotations.NotNull;

public final class LogsStatSupplier {
    private static final int MIN_INFORMATIONAL = 100;
    private static final int MAX_INFORMATIONAL = 199;
    private static final int MIN_SUCCESSFUL = 200;
    private static final int MAX_SUCCESSFUL = 299;
    private static final int MIN_REDIRECTION = 300;
    private static final int MAX_REDIRECTION = 399;
    private static final int MIN_CLIENT_ERROR = 400;
    private static final int MAX_CLIENT_ERROR = 499;
    private static final int MIN_SERVER_ERROR = 500;
    private static final int MAX_SERVER_ERROR = 599;

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

    @SuppressWarnings("ReturnCount")
    private static String getCodeClass(int code) {
        if (code >= MIN_INFORMATIONAL && code <= MAX_INFORMATIONAL) {
            return "Informational";
        }
        if (code >= MIN_SUCCESSFUL && code <= MAX_SUCCESSFUL) {
            return "Successful";
        }
        if (code >= MIN_REDIRECTION && code <= MAX_REDIRECTION) {
            return "Redirection";
        }
        if (code >= MIN_CLIENT_ERROR && code <= MAX_CLIENT_ERROR) {
            return "Client Error";
        }
        if (code >= MIN_SERVER_ERROR && code <= MAX_SERVER_ERROR) {
            return "Server error";
        }
        return "Undefined";
    }

    private LogsStatSupplier() {
    }
}

package edu.project3;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public final class LogsStatSupplier {
    public static final Map<Integer, String> STATUS_NAME
        = Map.ofEntries(
        Map.entry(200, "OK"),
        Map.entry(404, "Not Found"),
        Map.entry(500, "Internal Server Error")
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
                    STATUS_NAME.getOrDefault(log.status(), "Undefined")
                ),
                Collectors.counting()
            ));
    }

    private LogsStatSupplier() {
    }
}

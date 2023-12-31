package edu.hw5;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class Task1 {
    private static final int TIME_CONST = 60;

    public static String getAverageSessionTime(String[] sessions) {
        List<Duration> sessionTime = new ArrayList<>();
        for (var session : sessions) {
            var pattern = Pattern
                .compile("(\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2}) - (\\d{4}-\\d{2}-\\d{2}, \\d{2}:\\d{2})");
            var matcher = pattern.matcher(session);
            if (matcher.find()) {
                var sessionDuration = getDurationOfTheSession(matcher.group(1), matcher.group(2));
                sessionTime.add(sessionDuration);
            }
        }

        var averageResult = getAverageResult(sessionTime);
        var hours = averageResult / (int) Math.pow(TIME_CONST, 2);
        var minutes = averageResult / TIME_CONST - hours * TIME_CONST;

        return String.format(
            "%dh %dm\n",
            hours,
            minutes
        );
    }

    private static Duration getDurationOfTheSession(String sessionBegin, String sessionEnd) {
        var formattedSessionBegin = sessionBegin.replaceAll(", ", "T");
        var formattedSessionEnd = sessionEnd.replaceAll(", ", "T");

        var parsedStart = LocalDateTime.parse(formattedSessionBegin);
        var parsedEnd = LocalDateTime.parse(formattedSessionEnd);

        return Duration.between(parsedStart, parsedEnd);
    }

    private static long getAverageResult(List<Duration> sessionTime) {
        return sessionTime
            .stream()
            .mapToLong(Duration::getSeconds)
            .sum() / sessionTime.size();
    }

    private Task1() {
    }
}

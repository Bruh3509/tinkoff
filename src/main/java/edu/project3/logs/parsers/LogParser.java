package edu.project3.logs.parsers;

import edu.project3.logs.LogNGINX;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public sealed interface LogParser permits LogFileParser, LogHttpParser {
    DateTimeFormatter FORMATTER
        = DateTimeFormatter.ofPattern("d/MMM/uuuu:H:m:sZ");

    Pattern LOG_REGEX
        = Pattern.compile("(.*) - (.*) \\[(.*)] \"(.*)\" (.*) (.*) \"(.*)\" \"(.*)\"");

    List<LogNGINX> getLogsList();

    @SuppressWarnings("MagicNumber")
    default List<LogNGINX> parseRawLogs(List<String> rawLogs) {
        List<LogNGINX> resultLogs = new ArrayList<>();
        for (var log : rawLogs) {
            var matcher = LOG_REGEX.matcher(log);
            if (matcher.find()) {
                var requestAndResource = matcher.group(4).split(" ");
                var time = matcher.group(3).replace(" ", "");
                resultLogs.add(new LogNGINX(
                    matcher.group(1),
                    matcher.group(2),
                    OffsetDateTime.parse(time, FORMATTER),
                    LogNGINX.RequestEnum.valueOf(requestAndResource[0]),
                    requestAndResource[1],
                    Integer.parseInt(matcher.group(5)),
                    Integer.parseInt(matcher.group(6)),
                    matcher.group(7),
                    matcher.group(8)
                ));
            }
        }

        return resultLogs;
    }
}

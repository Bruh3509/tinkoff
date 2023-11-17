package edu.project3;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LogParser {
    private static final DateTimeFormatter formatter
        = DateTimeFormatter.ofPattern("d/MMM/uuuu:H:m:sZ");

    private static final Pattern logRegex
        = Pattern.compile("(.*) - (.*) \\[(.*)] \"(.*)\" (.*) (.*) \"(.*)\" \"(.*)\"");

    public static List<LogNGINX> getLogsList(Path path) {
        var logsFile = path.toFile();
        var rawLogs = readLogsFromFile(logsFile);
        return parseRawLogs(rawLogs);
    }

    private static List<String> readLogsFromFile(File logsFile) {
        List<String> rawLogs = new ArrayList<>();
        try (var fileReader = new BufferedReader(new FileReader(logsFile))) {
            var rawLog = fileReader.readLine();
            while (rawLog != null) {
                rawLogs.add(rawLog);
                rawLog = fileReader.readLine();
            }
            return rawLogs;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static List<LogNGINX> parseRawLogs(List<String> rawLogs) {
        List<LogNGINX> resultLogs = new ArrayList<>();
        for (var log : rawLogs) {
            var matcher = logRegex.matcher(log);
            if (matcher.find()) {
                var requestAndResource = matcher.group(4).split(" ");
                var time = matcher.group(3).replace(" ", "");
                resultLogs.add(new LogNGINX(
                    matcher.group(0),
                    matcher.group(2),
                    OffsetDateTime.parse(time, formatter),
                    LogNGINX.RequestEnum.valueOf(requestAndResource[0]),
                    requestAndResource[1].substring(1),
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

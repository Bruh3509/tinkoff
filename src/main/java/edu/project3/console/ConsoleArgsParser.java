package edu.project3.console;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.regex.Pattern;

public class ConsoleArgsParser {
    private final String consoleArgs;
    private final Pattern pattern
        = Pattern.compile("--path ([^-]*)(--from ([^ ]*))?( *--to ([^ ]*))?( *--format (.*))?");

    public ConsoleArgsParser(String[] consoleArgs) {
        this.consoleArgs = String.join(" ", consoleArgs);
    }

    public ConsoleArgs getConsoleArgs() {
        return new ConsoleArgs(getSources(), getFromDate(), getToDate(), getFormat());
    }

    private List<String> getSources() {
        List<String> sources = new ArrayList<>();

        var matcher = pattern.matcher(consoleArgs);
        if (matcher.find()) {
            for (String sourcesRule : matcher.group(1).split(" ")) {
                if (sourcesRule.endsWith("*")) {
                    try (var directoryStream = Files.newDirectoryStream(Path.of(sourcesRule.substring(
                        0,
                        sourcesRule.length() - 1
                    )))) {
                        directoryStream.forEach(file -> {
                            if (Files.isRegularFile(file)) {
                                sources.add(file.toString());
                            }
                        });
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    sources.add(sourcesRule);
                }
            }
        } else {
            throw new InputMismatchException();
        }

        return sources;
    }

    @SuppressWarnings("MagicNumber")
    private OffsetDateTime getFromDate() {
        var matcher = pattern.matcher(consoleArgs);
        if (matcher.find()) {
            var date = matcher.group(3);
            if (date != null) {
                return OffsetDateTime.parse(date);
            }
        }
        return OffsetDateTime.MIN;
    }

    @SuppressWarnings("MagicNumber")
    private OffsetDateTime getToDate() {
        var matcher = pattern.matcher(consoleArgs);
        if (matcher.find()) {
            var date = matcher.group(5);
            if (date != null) {
                return OffsetDateTime.parse(date);
            }
        }

        return OffsetDateTime.MAX;
    }

    @SuppressWarnings("MagicNumber")
    private String getFormat() {
        var matcher = pattern.matcher(consoleArgs);
        if (matcher.find()) {
            var format = matcher.group(7);
            if (format != null) {
                return format;
            }
        }
        return "console";
    }
}

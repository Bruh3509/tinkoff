package edu.project3.logs.parsers;

import edu.project3.logs.LogNGINX;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public final class LogFileParser implements LogParser {

    private final Path path;

    public LogFileParser(Path path) {
        this.path = path;
    }

    @Override
    public List<LogNGINX> getLogsList() {
        var logsFile = path.toFile();
        var rawLogs = readLogsFromFile(logsFile);
        return parseRawLogs(rawLogs);
    }

    private List<String> readLogsFromFile(File logsFile) {
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
}

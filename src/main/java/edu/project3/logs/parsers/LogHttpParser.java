package edu.project3.logs.parsers;

import edu.project3.logs.LogNGINX;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public final class LogHttpParser implements LogParser {
    private final URI logsUri;

    public LogHttpParser(URI logsUri) {
        this.logsUri = logsUri;
    }

    @Override
    public List<LogNGINX> getLogsList() {
        var rawLogs = readLogsFromURI();
        return parseRawLogs(rawLogs);
    }

    private List<String> readLogsFromURI() {
        HttpRequest request = HttpRequest.newBuilder(logsUri).build();
        try (HttpClient client = HttpClient.newBuilder().version(HttpClient.Version.HTTP_1_1).build()) {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            var responseBody = response.body().split("\n");
            return new ArrayList<>(Arrays.asList(responseBody));
        } catch (InterruptedException | IOException e) {
            throw new RuntimeException(e);
        }
    }
}

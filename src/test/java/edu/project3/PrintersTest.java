package edu.project3;

import edu.project3.logs.parsers.LogFileParser;
import edu.project3.logs.parsers.LogHttpParser;
import edu.project3.logs.printers.AdocPrinter;
import edu.project3.logs.printers.ConsolePrinter;
import edu.project3.logs.printers.MarkdownPrinter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;

public class PrintersTest {
    @Test
    @DisplayName("Test Console Print")
    void testConsolePrint() {
        // arrange
        Path path = Paths.get("src", "main", "resources", "logs.txt");
        URI uri = URI.create(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        var logsFileParser = new LogFileParser(path);
        var logs = logsFileParser.getLogsList();

        var logsHttpParser = new LogHttpParser(uri);
        var logs1 = logsHttpParser.getLogsList();

        // act
        var consolePrinter = new ConsolePrinter(logs, List.of(path), OffsetDateTime.MIN, OffsetDateTime.MAX);
        var consolePrinter1 = new ConsolePrinter(logs1, List.of(Path.of(uri.getPath())), OffsetDateTime.MIN, OffsetDateTime.MAX);

        consolePrinter.print();
        consolePrinter1.print();
    }

    @Test
    @DisplayName("Test Markdown")
    void testMarkDown() throws IOException {
        // arrange
        URI uri = URI.create(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        var logsHttpParser = new LogHttpParser(uri);
        var logs1 = logsHttpParser.getLogsList();

        // act
        var mdPrinter1= new MarkdownPrinter(logs1, List.of(Path.of(uri.getPath())), OffsetDateTime.MIN, OffsetDateTime.MAX);

        mdPrinter1.print();
    }

    @Test
    @DisplayName("Test Adoc")
    void testAdoc() throws IOException {
        // arrange
        URI uri = URI.create(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        var logsHttpParser = new LogHttpParser(uri);
        var logs1 = logsHttpParser.getLogsList();

        // act
        var adocPrinter1= new AdocPrinter(logs1, List.of(Path.of(uri.getPath())), OffsetDateTime.MIN, OffsetDateTime.MAX);

        adocPrinter1.print();
    }
}

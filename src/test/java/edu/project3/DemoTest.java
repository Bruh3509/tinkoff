package edu.project3;

import edu.project3.logs.parsers.LogFileParser;
import edu.project3.logs.parsers.LogHttpParser;
import edu.project3.logs.printers.ConsolePrinter;
import edu.project3.logs.printers.InfoPrinter;
import edu.project3.logs.printers.MarkdownPrinter;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

public class DemoTest {
    @Test
    @DisplayName("Demo Test")
    void testOutput() throws IOException {
        Path path = Paths.get("src", "main", "resources", "logs.txt");
        URI uri = URI.create(
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");

        var logsFileParser = new LogFileParser(path);
        var logs = logsFileParser.getLogsList();

        var logsHttpParser = new LogHttpParser(uri);
        var logs1 = logsHttpParser.getLogsList();

        InfoPrinter printer =
            new ConsolePrinter(logs1, List.of(Path.of(uri.getPath())),
                OffsetDateTime.of(2015, 5, 17, 14,
                    0, 0, 0,
                    ZoneOffset.ofHours(0)
                ), OffsetDateTime.of(2015, 5, 17, 16,
                0, 0, 0,
                ZoneOffset.ofHours(0)
            )
            );
        printer.print();

        InfoPrinter printer1 = new MarkdownPrinter(logs1, List.of(Path.of(uri.getPath())),
            OffsetDateTime.of(2015, 5, 17, 14,
                0, 0, 0,
                ZoneOffset.ofHours(0)
            ), OffsetDateTime.of(2015, 5, 17, 16,
            0, 0, 0,
            ZoneOffset.ofHours(0)
        )
        );
        printer1.print();
    }
}

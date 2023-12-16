package edu.project3;

import edu.project3.logs.LogNGINX;
import edu.project3.logs.parsers.LogFileParser;
import edu.project3.logs.parsers.LogHttpParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.net.URI;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import static org.assertj.core.api.Assertions.assertThat;

public class LogParsersTest {
    @Test
    @DisplayName("Test Log File Parser")
    void testLogFileParser() {
        // arrange
        Path path = Path.of("src/main/resources/short_logs.txt");
        var logFileParser = new LogFileParser(path);

        // act
        var logs = logFileParser.getLogsList();

        // assert
        assertThat(logs)
            .containsExactlyInAnyOrder(
                new LogNGINX(
                    "109.62.203.34",
                    "-",
                    OffsetDateTime.of(2023, 11, 17, 13, 11, 55, 0, ZoneOffset.ofHours(0)),
                    LogNGINX.RequestEnum.GET,
                    "/Distributed-extranet/data-warehouse_encryption-Realigned.jpg",
                    200,
                    1509,
                    "-",
                    "Mozilla/5.0 (Macintosh; U; PPC Mac OS X 10_9_6 rv:5.0; en-US) AppleWebKit/533.43.2 (KHTML, like Gecko) Version/5.1 Safari/533.43.2"
                ),
                new LogNGINX(
                    "218.37.105.115",
                    "-",
                    OffsetDateTime.of(2023, 11, 17, 13, 11, 55, 0, ZoneOffset.ofHours(0)),
                    LogNGINX.RequestEnum.HEAD,
                    "/Inverse-Right-sized.js",
                    200,
                    1194,
                    "-",
                    "Mozilla/5.0 (iPhone; CPU iPhone OS 8_1_1 like Mac OS X; en-US) AppleWebKit/535.14.1 (KHTML, like Gecko) Version/3.0.5 Mobile/8B119 Safari/6535.14.1"
                ),
                new LogNGINX(
                    "44.191.39.6",
                    "-",
                    OffsetDateTime.of(2023, 11, 17, 13, 11, 55, 0, ZoneOffset.ofHours(0)),
                    LogNGINX.RequestEnum.HEAD,
                    "/algorithm-solution-oriented-coherent%20monitoring/clear-thinking.jpg",
                    200,
                    1505,
                    "-",
                    "Mozilla/5.0 (Macintosh; PPC Mac OS X 10_6_1 rv:6.0) Gecko/1991-07-11 Firefox/35.0"
                ),
                new LogNGINX(
                    "216.33.75.200",
                    "-",
                    OffsetDateTime.of(2023, 11, 17, 13, 11, 55, 0, ZoneOffset.ofHours(0)),
                    LogNGINX.RequestEnum.GET,
                    "/utilisation/analyzer-infrastructure/directional.htm",
                    200,
                    3100,
                    "-",
                    "Mozilla/5.0 (Windows; U; Windows NT 6.0) AppleWebKit/536.13.5 (KHTML, like Gecko) Version/6.1 Safari/536.13.5"
                )
            );
    }

    @Test
    @DisplayName("Test Log Http Parser")
    void testLogHttpParser() {
        // arrange
        URI uri = URI.create("https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        var logHttpParser = new LogHttpParser(uri);

        // act
        var logs = logHttpParser.getLogsList();

        // assert
        assertThat(logs.size()).isEqualTo(51462);
    }
}

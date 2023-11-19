package edu.project3;

import edu.project3.logs.LogsStatSupplier;
import edu.project3.logs.parsers.LogFileParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Map;
import static org.assertj.core.api.Assertions.assertThat;

public class LogStatSupplierTest {
    @Test
    @DisplayName("Test Requests Amount")
    void testRequestsAmount() {
        Path path = Path.of("src/main/resources/short_logs.txt");
        var logFileParser = new LogFileParser(path);
        var logs = logFileParser.getLogsList();

        // act
        var requestsAmount = LogsStatSupplier.getRequestsAmount(logs);

        // assert
        assertThat(requestsAmount).isEqualTo(4);
    }

    @Test
    @DisplayName("Test Average Response Size")
    void testAverageResponseSize() {
        // arrange
        Path path = Path.of("src/main/resources/short_logs.txt");
        var logFileParser = new LogFileParser(path);
        var logs = logFileParser.getLogsList();

        // act
        var averageResponseSize = LogsStatSupplier.getAverageResponseSize(logs);

        // assert
        assertThat(averageResponseSize).isEqualTo("1827b");
    }

    @Test
    @DisplayName("Test Resources Frequency")
    void testResourcesFreq() {
        // arrange
        Path path = Path.of("src/main/resources/short_logs.txt");
        var logFileParser = new LogFileParser(path);
        var logs = logFileParser.getLogsList();

        // act
        var resourcesFreq = LogsStatSupplier.getTheResourcesFrequency(logs);

        // assert
        assertThat(resourcesFreq).containsExactlyInAnyOrderEntriesOf(
            Map.ofEntries(
                Map.entry("/Distributed-extranet/data-warehouse_encryption-Realigned.jpg", 1L),
                Map.entry("/Inverse-Right-sized.js", 1L),
                Map.entry("/algorithm-solution-oriented-coherent%20monitoring/clear-thinking.jpg", 1L),
                Map.entry("/utilisation/analyzer-infrastructure/directional.htm", 1L)
            )
        );
    }

    @Test
    @DisplayName("Test Response Codes Count")
    void testGetResponseCodesCount() {
        // arrange
        Path path = Path.of("src/main/resources/short_logs.txt");
        var logFileParser = new LogFileParser(path);
        var logs = logFileParser.getLogsList();

        // act
        var responseCodesCount = LogsStatSupplier.getResponseCodesCount(logs);

        // assert
        assertThat(responseCodesCount).containsExactlyInAnyOrderEntriesOf(
            Map.ofEntries(
                Map.entry(Map.entry(200, "OK"), 4L)
            )
        );
    }

    @Test
    @DisplayName("Test Filtered By Date")
    void testFilteredByDate() {
        // arrange
        Path path = Path.of("src/main/resources/short_logs.txt");
        var logFileParser = new LogFileParser(path);
        var logs = logFileParser.getLogsList();
        OffsetDateTime from = OffsetDateTime.of(2020, 10, 10, 10, 10, 10, 0, ZoneOffset.ofHours(0));
        OffsetDateTime to = OffsetDateTime.of(2023, 12, 10, 10, 10, 10, 0, ZoneOffset.ofHours(0));

        // act
        var filteredLogs = LogsStatSupplier.getFilteredByDateLogs(logs, from, to);
        var emptyLogs = LogsStatSupplier.getFilteredByDateLogs(logs, OffsetDateTime.MAX, OffsetDateTime.MAX);

        // assert
        assertThat(logs).isEqualTo(filteredLogs);
        assertThat(emptyLogs).isEmpty();
    }
}

package edu.project3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.nio.file.Path;
import java.nio.file.Paths;

public class DemoTest {
    @Test
    @DisplayName("Demo Test")
    void testOutput() {
        Path path = Paths.get("src", "main", "resources", "logs.txt");
        var logs = LogParser.getLogsList(path);

        /*var averageResponseSize = LogsStatSupplier.getAverageResponseSize(logs);
        System.out.println(averageResponseSize);

        var responseCodesCount = LogsStatSupplier.getResponseCodesCount(logs);
        System.out.println(responseCodesCount);

        var s = LogsStatSupplier.getTheResourcesFrequency(logs);
        System.out.println(s);
         */

        InfoPrinter printer = new ConsolePrinter(logs, path, null, null);

        printer.print();
    }
}

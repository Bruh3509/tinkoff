package edu.project3;

import org.apache.logging.log4j.core.appender.routing.Route;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class ConsolePrinter implements InfoPrinter {
    private final List<LogNGINX> logs;
    private final Path path;
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private final Logger consoleLogger = Logger.getLogger("Console Logger");

    public ConsolePrinter(List<LogNGINX> logs, Path path, OffsetDateTime from, OffsetDateTime to) {
        this.logs = logs;
        this.path = path;
        this.from = from;
        this.to = to;
    }

    @Override
    public void print() {
        System.out.println("\t\tGeneral information");
        printGeneralInformation();

        System.out.println("\tResource\t\tAmount");
        printRequestedResources();
    }

    private void printGeneralInformation() {
        System.out.println("\tMetrics\t\tValue");
        System.out.println("File(s)\t\t" + path.toString());
        System.out.println("Start data\t\t" + from);
        System.out.println("End data\t\t" + to);
        System.out.println("Requests amount\t\t" + LogsStatSupplier.getRequestsAmount(logs));
        System.out.println("Average response size\t\t" + LogsStatSupplier.getAverageResponseSize(logs));
    }

    private void printRequestedResources() {
        LogsStatSupplier.getTheResourcesFrequency(logs).entrySet().stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .forEach(System.out::println);
    }
}

package edu.project3.logs.printers;

import edu.project3.logs.LogNGINX;
import edu.project3.logs.LogsStatSupplier;
import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;
import org.jetbrains.annotations.NotNull;

public final class ConsolePrinter implements InfoPrinter {
    private static final int MAX_RESOURCES = 5;
    private static final int MAX_STATUS_CODES = 5;
    private final List<LogNGINX> logs;
    private final List<Path> path;
    private final OffsetDateTime from;
    private final OffsetDateTime to;

    public ConsolePrinter(
        List<LogNGINX> logs,
        List<Path> path,
        @NotNull OffsetDateTime from,
        @NotNull OffsetDateTime to
    ) {
        this.logs = LogsStatSupplier.getFilteredByDateLogs(logs, from, to);
        this.path = path;
        this.from = from;
        this.to = to;
    }

    @Override
    @SuppressWarnings("RegexpSinglelineJava")
    public void print() {
        System.out.println("\tGeneral information");
        printGeneralInformation();

        System.out.println("\tResource&Amount");
        printRequestedResources();

        System.out.println("\tStatus Codes");
        printStatusCodes();
    }

    @Override
    @SuppressWarnings("RegexpSinglelineJava")
    public void printGeneralInformation() {
        System.out.println("Metrics         Value");
        System.out.println("File(s)         " + path.toString());
        System.out.println("Start data      " + from);
        System.out.println("End data        " + to);
        System.out.println("Requests amount " + LogsStatSupplier.getRequestsAmount(logs));
        System.out.println("Average size    " + LogsStatSupplier.getAverageResponseSize(logs));
    }

    @Override
    @SuppressWarnings("RegexpSinglelineJava")
    public void printRequestedResources() {
        LogsStatSupplier.getTheResourcesFrequency(logs)
            .entrySet()
            .stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(MAX_RESOURCES)
            .forEach(System.out::println);
    }

    @Override
    @SuppressWarnings("RegexpSinglelineJava")
    public void printStatusCodes() {
        LogsStatSupplier.getResponseCodesCount(logs)
            .entrySet()
            .stream()
            .sorted(Map.Entry.<Map.Entry<Integer, String>, Long>comparingByValue().reversed())
            .limit(MAX_STATUS_CODES)
            .forEach(System.out::println);
    }
}

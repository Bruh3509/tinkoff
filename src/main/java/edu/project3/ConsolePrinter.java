package edu.project3;

import java.nio.file.Path;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public final class ConsolePrinter implements InfoPrinter {
    private final List<LogNGINX> logs;
    private final Path path;
    private final OffsetDateTime from;
    private final OffsetDateTime to;

    public ConsolePrinter(List<LogNGINX> logs, Path path, OffsetDateTime from, OffsetDateTime to) {
        this.logs = logs;
        this.path = path.getFileName();
        this.from = from;
        this.to = to;
    }

    @Override
    public void print() {
        System.out.println("\tGeneral information");
        printGeneralInformation();

        System.out.println("\tResource&Amount");
        printRequestedResources();

        System.out.println("\tStatus Codes");
        printStatusCodes();
    }

    @Override
    public void printGeneralInformation() {
        System.out.println("Metrics         Value");
        System.out.println("File(s)         " + path.toString());
        System.out.println("Start data      " + from);
        System.out.println("End data        " + to);
        System.out.println("Requests amount " + LogsStatSupplier.getRequestsAmount(logs));
        System.out.println("Average size    " + LogsStatSupplier.getAverageResponseSize(logs));
    }

    @Override
    public void printRequestedResources() {
        LogsStatSupplier.getTheResourcesFrequency(logs)
            .entrySet()
            .stream()
            .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
            .limit(5)
            .forEach(System.out::println);
    }

    @Override
    public void printStatusCodes() {
        LogsStatSupplier.getResponseCodesCount(logs)
            .entrySet()
            .stream()
            .sorted(Map.Entry.<Map.Entry<Integer, String>, Long>comparingByValue().reversed())
            .limit(5)
            .forEach(System.out::println);
    }
}

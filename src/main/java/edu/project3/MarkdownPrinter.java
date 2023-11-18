package edu.project3;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.OffsetDateTime;
import java.util.List;
import java.util.Map;

public final class MarkdownPrinter implements InfoPrinter {
    private final List<LogNGINX> logs;
    private final Path path;
    private final OffsetDateTime from;
    private final OffsetDateTime to;
    private final File outputFile;

    public MarkdownPrinter(List<LogNGINX> logs, Path path, OffsetDateTime from, OffsetDateTime to) throws IOException {
        this.logs = logs;
        this.path = path.getFileName();
        this.from = from;
        this.to = to;
        Path outputPath = Paths.get(path.getParent().toString(), "output.md");
        if (Files.exists(outputPath)) {
            Files.delete(outputPath);
        }
        outputFile = new File(outputPath.toString());
    }

    @Override
    public void print() {
        printGeneralInformation();
        printRequestedResources();
        printStatusCodes();
    }

    @Override
    public void printGeneralInformation() {
        try (var fileWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            fileWriter.write("#### General Information\n");
            fileWriter.write("| Metrics | Value |\n");
            fileWriter.write("|:---------------------:|:-------------:|\n");
            fileWriter.write(String.format("| Files |`%s`|\n", path));
            fileWriter.write(String.format("| Start date |%s|\n", from));
            fileWriter.write(String.format("| End date |%s|\n", to));
            fileWriter.write(String.format(
                "| Requests Amount |%d|\n",
                LogsStatSupplier.getRequestsAmount(logs)
            ));
            fileWriter.write(String.format(
                "| Average Size |%s|\n",
                LogsStatSupplier.getAverageResponseSize(logs)
            ));
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printRequestedResources() {
        try (var fileWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            fileWriter.write("#### Requested Resources\n");
            fileWriter.write("| Resource | Amount |\n");
            fileWriter.write("|:---------------:|:-----------:|\n");
            LogsStatSupplier.getTheResourcesFrequency(logs)
                .entrySet()
                .stream()
                .sorted(Map.Entry.<String, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    try {
                        fileWriter.write(String.format(
                            "| `%s` | %d |\n",
                            entry.getKey(),
                            entry.getValue()
                        ));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
            fileWriter.write("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void printStatusCodes() {
        try (var fileWriter = new BufferedWriter(new FileWriter(outputFile, true))) {
            fileWriter.write("#### Status Codes\n");
            fileWriter.write("| Code | Name | Amount |\n");
            fileWriter.write("|:---:|:---------------------:|-----------:|\n");
            LogsStatSupplier.getResponseCodesCount(logs)
                .entrySet()
                .stream()
                .sorted(Map.Entry.<Map.Entry<Integer, String>, Long>comparingByValue().reversed())
                .limit(5)
                .forEach(entry -> {
                    try {
                        fileWriter.write(String.format(
                            "| %d | %s | %d |\n",
                            entry.getKey().getKey(),
                            entry.getKey().getValue(),
                            entry.getValue()
                        ));
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

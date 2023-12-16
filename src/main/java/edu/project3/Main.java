package edu.project3;

import edu.project3.console.ConsoleArgsParser;
import edu.project3.logs.LogNGINX;
import edu.project3.logs.parsers.LogFileParser;
import edu.project3.logs.parsers.LogHttpParser;
import edu.project3.logs.parsers.LogParser;
import edu.project3.logs.printers.AdocPrinter;
import edu.project3.logs.printers.ConsolePrinter;
import edu.project3.logs.printers.InfoPrinter;
import edu.project3.logs.printers.MarkdownPrinter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        if (args.length > 1) {
            var consoleArgsParser = new ConsoleArgsParser(args);
            var consoleArgs = consoleArgsParser.getConsoleArgs();
            var sources = consoleArgs.sources();

            LogParser parser;
            List<LogNGINX> resultLogs = new ArrayList<>();
            List<Path> sourcePaths = new ArrayList<>();

            for (var source : sources) {
                if (source.matches("http(s)?://.*")) {
                    var uri = URI.create(source);
                    parser = new LogHttpParser(uri);
                    sourcePaths.add(Path.of(uri.getPath()));
                } else {
                    var path = Path.of(source);
                    parser = new LogFileParser(path);
                    sourcePaths.add(path);
                }

                resultLogs.addAll(parser.getLogsList());
            }

            InfoPrinter printer = switch (consoleArgs.outputFormat()) {
                case "console" -> new ConsolePrinter(resultLogs, sourcePaths,
                    consoleArgs.from(), consoleArgs.to()
                );
                case "markdown" -> new MarkdownPrinter(resultLogs, sourcePaths,
                    consoleArgs.from(), consoleArgs.to()
                );
                case "adoc" -> new AdocPrinter(resultLogs, sourcePaths,
                    consoleArgs.from(), consoleArgs.to()
                );
                default -> throw new InputMismatchException();
            };

            printer.print();
        } else {
            throw new InputMismatchException();
        }
    }

    private Main() {
    }
}

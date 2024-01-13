package edu.project3.console;

import java.time.OffsetDateTime;
import java.util.List;

public record ConsoleArgs(List<String> sources, OffsetDateTime from, OffsetDateTime to, String outputFormat) {
}

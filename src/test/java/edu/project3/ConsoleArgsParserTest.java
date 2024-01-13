package edu.project3;

import edu.project3.console.ConsoleArgsParser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.OffsetDateTime;
import java.util.InputMismatchException;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class ConsoleArgsParserTest {
    @Test
    @DisplayName("Args Parser Test")
    void testArgsParserValid() {
        // arrange
        String[] args1 = new String[] {"--path", "src/main/resources/logs.txt",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "--from 2010-10-11T00:00:00+00", "--to 2024-10-10T16:00:00+00", "--format adoc"};
        var argsParser1 = new ConsoleArgsParser(args1);

        String[] args2 = new String[] {"--path", "src/main/resources/logs.txt",
            "--to 2024-10-10T16:00:00+00", "--format markdown"};
        var argsParser2 = new ConsoleArgsParser(args2);

        String[] args3 = new String[] {"--path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs",
            "--from 2020-10-10T16:00:00+00", "--format console"};
        var argsParser3 = new ConsoleArgsParser(args3);

        String[] args4 = new String[] {"--path", "src/main/resources/logs.txt",
            "--format adoc"};
        var argsParser4 = new ConsoleArgsParser(args4);

        String[] args5 = new String[] {"--path",
            "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"};
        var argsParser5 = new ConsoleArgsParser(args5);

        String[] args6 = new String[] {"--path", "src/main/resources/*"};
        var argsParser6 = new ConsoleArgsParser(args6);

        // act
        var consoleArgs1 = argsParser1.getConsoleArgs();
        var consoleArgs2 = argsParser2.getConsoleArgs();
        var consoleArgs3 = argsParser3.getConsoleArgs();
        var consoleArgs4 = argsParser4.getConsoleArgs();
        var consoleArgs5 = argsParser5.getConsoleArgs();
        var consoleArgs6 = argsParser6.getConsoleArgs();

        // assert
        // args1
        assertThat(consoleArgs1.sources())
            .containsExactlyInAnyOrder(
                "src/main/resources/logs.txt",
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"
            );
        assertThat(consoleArgs1.from()).isEqualTo("2010-10-11T00:00:00+00");
        assertThat(consoleArgs1.to()).isEqualTo("2024-10-10T16:00:00+00");
        assertThat(consoleArgs1.outputFormat()).isEqualTo("adoc");

        // args2
        assertThat(consoleArgs2.sources())
            .containsExactlyInAnyOrder(
                "src/main/resources/logs.txt"
            );
        assertThat(consoleArgs2.from()).isEqualTo(OffsetDateTime.MIN);
        assertThat(consoleArgs2.to()).isEqualTo("2024-10-10T16:00:00+00");
        assertThat(consoleArgs2.outputFormat()).isEqualTo("markdown");

        // args3
        assertThat(consoleArgs3.sources())
            .containsExactlyInAnyOrder(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs"
            );
        assertThat(consoleArgs3.from()).isEqualTo("2020-10-10T16:00:00+00");
        assertThat(consoleArgs3.to()).isEqualTo(OffsetDateTime.MAX);
        assertThat(consoleArgs3.outputFormat()).isEqualTo("console");

        // args4
        assertThat(consoleArgs4.sources())
            .containsExactlyInAnyOrder("src/main/resources/logs.txt");
        assertThat(consoleArgs4.from()).isEqualTo(OffsetDateTime.MIN);
        assertThat(consoleArgs4.to()).isEqualTo(OffsetDateTime.MAX);
        assertThat(consoleArgs4.outputFormat()).isEqualTo("adoc");

        // args5
        assertThat(consoleArgs5.sources())
            .containsExactlyInAnyOrder(
                "https://raw.githubusercontent.com/elastic/examples/master/Common%20Data%20Formats/nginx_logs/nginx_logs");
        assertThat(consoleArgs5.from()).isEqualTo(OffsetDateTime.MIN);
        assertThat(consoleArgs5.to()).isEqualTo(OffsetDateTime.MAX);
        assertThat(consoleArgs5.outputFormat()).isEqualTo("console");

        // args6
        assertThat(consoleArgs6.sources())
            .containsExactlyInAnyOrder(
                "src/main/resources/log4j2.xml",
                "src/main/resources/logs.txt",
                "src/main/resources/output.adoc",
                "src/main/resources/output.md",
                "src/main/resources/short_logs.txt",
                "src/main/resources/img1067.png",
                "src/main/resources/0.png",
                "src/main/resources/img5983.png",
                "src/main/resources/img8634.png",
                "src/main/resources/img4017.png",
                "src/main/resources/cache.txt",
                "src/main/resources/img5465.png",
                "src/main/resources/img6487.png",
                "src/main/resources/img2458.png",
                "src/main/resources/img9354.png",
                "src/main/resources/img6400.png",
                "src/main/resources/stats_jmh.md",
                "src/main/resources/img5487.png",
                "src/main/resources/img9196.png",
                "src/main/resources/img427.png"
            );
    }

    @Test
    @DisplayName("Args Parser Not Valid Test")
    void testArgsParserNotValid() {
        // arrange
        String[] args6 = new String[] {"--from 2010-10-11T00:00:00+00", "--to 2024-10-10T16:00:00+00", "--format adoc"};
        var argsParser6 = new ConsoleArgsParser(args6);

        // act + assert
        assertThrows(InputMismatchException.class, argsParser6::getConsoleArgs);
    }
}

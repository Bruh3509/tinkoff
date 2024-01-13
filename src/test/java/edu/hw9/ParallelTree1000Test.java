package edu.hw9;

import edu.hw9.task2.ParallelTree1000;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import static org.assertj.core.api.Assertions.assertThat;

public class ParallelTree1000Test {
    @Test
    @DisplayName("Parallel Test")
    void testParallel1000() {
        // arrange
        final int minFiles1 = 11;
        final int minFiles2 = 7;
        final Path rootDir = Path.of(".");
        final var predictRes1 = List.of(
        );
        final var predictRes2 = List.of(
            Path.of(".", "")
        );

        // act
        List<Path> res1;
        List<Path> res2;
        try (var executorService = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
            var task1 = executorService.submit(new ParallelTree1000(rootDir, minFiles1));
            var task2 = executorService.submit(new ParallelTree1000(rootDir, minFiles2));
            res1 = task1.get();
            res2 = task2.get();
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // assert
        Path path = Path.of(".", "target", "classes", "edu", "project2");
        Path path1 = Path.of(".", "src", "main", "java", "edu", "project2");
        Path path2 = Path.of(".", "src", "main", "resources");
        Path path3 = Path.of(".", "target", "classes", "edu", "project4");
        Path path4 = Path.of(".", "src", "main", "java", "edu", "project4");
        Path path5 = Path.of(".", "target", "generated-sources", "annotations", "edu", "project5", "jmh_generated");
        Path path6 = Path.of(".", "target", "classes", "edu", "project5", "jmh_generated");
        assertThat(res1).containsExactlyInAnyOrder(
            path,
            path1,
            path2,
            path3,
            path4
        );
        assertThat(res2).containsExactlyInAnyOrder(
            path,
            path1,
            path2,
            path3,
            path4,
            path5,
            path6,
            Path.of(".", "src", "main", "java", "edu", "hw1"),
            Path.of(".", "src", "main", "java", "edu", "hw2", "task3"),
            Path.of(".", "src", "main", "java", "edu", "hw5"),
            Path.of(".", "src", "main", "java", "edu", "project1"),
            Path.of(".", "src", "test", "java", "edu", "hw3"),
            Path.of(".", "src", "test", "java", "edu", "hw5"),
            Path.of(".", "target", "classes", "edu", "hw1"),
            Path.of(".", "target", "classes", "edu", "hw2", "task3"),
            Path.of(".", "target", "classes", "edu", "hw5"),
            Path.of(".", "target", "classes", "edu", "project1"),
            Path.of(".", "target", "test-classes", "edu", "hw3"),
            Path.of(".", "target", "test-classes", "edu", "hw5")
        );
    }
}

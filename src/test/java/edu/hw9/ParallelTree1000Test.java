package edu.hw9;

import edu.hw9.task2.ParallelTree1000;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ParallelTree1000Test {
    @Test
    @DisplayName("Parallel Test")
    void testParallel1000() {
        // arrange
        try (var executorService = new ForkJoinPool(Runtime.getRuntime().availableProcessors())) {
            var res = executorService.submit(new ParallelTree1000(Path.of("."), 10));
            var resultList = res.get();
            System.out.println(resultList);
        } catch (InterruptedException | ExecutionException e) {
            throw new RuntimeException(e);
        }

        // act

        // assert
    }
}

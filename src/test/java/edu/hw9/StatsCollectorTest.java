package edu.hw9;

import edu.hw9.task1.StatsCollector;
import org.assertj.core.data.Offset;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.concurrent.ExecutionException;
import static org.assertj.core.api.Assertions.assertThat;

public class StatsCollectorTest {
    @Test
    @DisplayName("Test Stats Collector Multithread")
    void testStatsCollector() {
        // arrange
        var statsCollector = new StatsCollector();
        var thread1 = new Thread(() -> {
            try {
                statsCollector.push(
                    "1",
                    new Double[] {1., 0.5, 0.2, 1.2, 4.3}
                );
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });
        var thread2 = new Thread(() -> {
            try {
                statsCollector.push(
                    "2",
                    new Double[] {2., 1.5, 0.3, 1.7, 6.5}
                );
            } catch (ExecutionException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        });

        // act
        thread1.start();
        thread2.start();

        // assert
        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        for (var stat : statsCollector.stats()) {
            var value = stat.getValue();
            var key = stat.getKey();
            if (key.equals("1")) {
                assertThat(value.sum()).isCloseTo(7.2, Offset.offset(0.0001));
                assertThat(value.average()).isCloseTo(1.44, Offset.offset(0.0001));
                assertThat(value.max()).isCloseTo(4.3, Offset.offset(0.0001));
                assertThat(value.min()).isCloseTo(0.2, Offset.offset(0.0001));
            } else if (key.equals("2")) {
                assertThat(value.sum()).isCloseTo(12, Offset.offset(0.0001));
                assertThat(value.average()).isCloseTo(2.4, Offset.offset(0.0001));
                assertThat(value.max()).isCloseTo(6.5, Offset.offset(0.0001));
                assertThat(value.min()).isCloseTo(0.3, Offset.offset(0.0001));
            }
        }
    }
}

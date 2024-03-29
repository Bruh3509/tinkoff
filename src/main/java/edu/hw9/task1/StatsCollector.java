package edu.hw9.task1;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class StatsCollector {
    private final ConcurrentHashMap<String, Stats> statsMap;
    private static final ExecutorService THREAD_POOL = Executors.newFixedThreadPool(Runtime.getRuntime()
        .availableProcessors());

    public StatsCollector() {
        statsMap = new ConcurrentHashMap<>();
    }

    public void push(String metricsName, Double[] data) throws ExecutionException, InterruptedException {
        var statsFuture = THREAD_POOL
            .submit(() -> {
                var sum = data[0];
                var max = data[0];
                var min = data[0];
                for (int i = 1; i < data.length; ++i) {
                    var cur = data[i];
                    sum += cur;
                    if (cur > max) {
                        max = cur;
                    }
                    if (cur < min) {
                        min = cur;
                    }
                }
                return new Stats(sum, sum / data.length, max, min);
            });

        statsMap.put(metricsName, statsFuture.get());
    }

    public Set<Map.Entry<String, Stats>> stats() {
        return statsMap.entrySet();
    }
}

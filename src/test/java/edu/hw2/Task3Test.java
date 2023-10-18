package edu.hw2;

import edu.hw2.task3.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task3Test {
    private static final int TEST_COUNT = 100;

    @Test
    @DisplayName("Executor(DefaultConnectionManager)")
    void testExecutorDefaultConnectionManager() {
        // arrange
        int attempts = 2;
        var connectionManager = new DefaultConnectionManager();
        var commandExecutor = new PopularCommandExecutor(connectionManager, attempts);

        // act
        for (int i = 0; i < TEST_COUNT; ++i) {
            try {
                commandExecutor.updatePackages();
            } catch (ConnectionException ignored) {
            }
        }
    }

    @Test
    @DisplayName("Executor(FaultyConnectionManager)")
    void testExecutorFaultyConnectionManager() {
        // arrange
        int attempts = 3;
        var connectionManager = new FaultyConnectionManager();
        var commandExecutor = new PopularCommandExecutor(connectionManager, attempts);

        // act
        for (int i = 0; i < TEST_COUNT; ++i) {
            try {
                commandExecutor.updatePackages();
            } catch (ConnectionException ignored) {
            }
        }
    }
}

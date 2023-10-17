package edu.hw2.task3;

import java.util.Random;

public class FaultyConnection implements Connection {
    private static final int RANDOM_CHANCE = 2;

    @Override
    public void execute(String command) {
        Random random = new Random();
        int nextInt = random.nextInt();
        if (nextInt % RANDOM_CHANCE == 0) {
            throw new ConnectionException();
        }
        PopularCommandExecutor.LOGGER.info("Command " + command + " has been executed.\n");
    }

    @Override
    public void close() throws Exception {
        PopularCommandExecutor.LOGGER.info("Connection has been closed.\n");
    }
}

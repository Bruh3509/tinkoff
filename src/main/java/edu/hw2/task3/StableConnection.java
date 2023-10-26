package edu.hw2.task3;

public class StableConnection implements Connection {
    @Override
    public void execute(String command) {
        PopularCommandExecutor.LOGGER.info("Command " + command + " has been executed.\n");
    }

    @Override
    public void close() throws Exception {
        PopularCommandExecutor.LOGGER.info("Connection has been closed.\n");
    }
}

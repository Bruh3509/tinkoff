package edu.hw2.task3;

import edu.hw2.task3.ConnectionException;
import edu.hw2.task3.ConnectionManager;
import java.util.logging.Logger;

public final class PopularCommandExecutor {
    public static final Logger LOGGER = Logger.getLogger("Connection Logger");
    private final ConnectionManager manager;
    private final int maxAttempts;

    public PopularCommandExecutor(ConnectionManager manager, int maxAttempts) {
        this.manager = manager;
        this.maxAttempts = maxAttempts;
    }

    public void updatePackages() {
        tryExecute("apt update && apt upgrade -y");
    }

    void tryExecute(String command) {
        int curAttempts = maxAttempts;
        while (curAttempts-- != 0) {
            try {
                manager.getConnection().execute(command);
                return;
            } catch (ConnectionException exception) {
                if (curAttempts == 0) {
                    LOGGER.info("Fail to execute command " + command + ".\n");
                    throw new ConnectionException(exception);
                }
            }
        }
    }
}

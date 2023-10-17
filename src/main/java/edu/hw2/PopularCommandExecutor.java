package edu.hw2;

public final class PopularCommandExecutor {
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
                }
            }
        }
    }
}

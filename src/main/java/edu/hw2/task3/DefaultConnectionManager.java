package edu.hw2.task3;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    private static final int RANDOM_CHANCE = 2;

    @Override
    public Connection getConnection() {
        Random random = new Random();
        int nextInt = random.nextInt();
        if (nextInt % RANDOM_CHANCE == 0) {
            return new FaultyConnection();
        }

        return new StableConnection();
    }
}

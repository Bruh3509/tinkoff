package edu.hw2;

import java.util.Random;

public class DefaultConnectionManager implements ConnectionManager {
    @Override
    public Connection getConnection() {
        Random random = new Random();
        if (random.nextInt() % 10 == 0) {
            return new FaultyConnection();
        }

        return new StableConnection();
    }
}

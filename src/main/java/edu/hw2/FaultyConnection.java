package edu.hw2;

import java.util.Random;

public class FaultyConnection implements Connection {
    @Override
    public void execute(String command) {
        Random random = new Random();
        if (random.nextInt() % 10 == 0) {
            throw new ConnectionException();
        }
    }

    @Override
    public void close() throws Exception {

    }
}

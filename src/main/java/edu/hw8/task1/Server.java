package edu.hw8.task1;

import java.io.IOException;

public class Server {
    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws IOException {
        ServerImpl server = new ServerImpl();
        server.start();
    }

    private Server() {
    }
}

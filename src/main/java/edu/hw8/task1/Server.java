package edu.hw8.task1;

import java.io.IOException;

public class Server {
    public static void main(String[] args) throws IOException {
        MyServer server = new MyServer();
        server.start();
    }
}

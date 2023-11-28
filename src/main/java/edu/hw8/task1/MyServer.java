package edu.hw8.task1;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class MyServer extends ServerSocket {

    public MyServer(int port) throws IOException {
        super(port);
    }
}

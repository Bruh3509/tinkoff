package edu.hw8.task1;

import java.io.IOException;

public class Client2 {
    public static void main(String[] args) throws IOException {
        var client2 = new ClientImpl();
        while (true) {
            client2.sendToServer();
        }
    }
}

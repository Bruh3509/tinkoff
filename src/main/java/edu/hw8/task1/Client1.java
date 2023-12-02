package edu.hw8.task1;

public class Client1 {
    public static void main(String[] args) throws Exception {
        var client1 = new ClientImpl();
        while (true) {
            client1.sendToServer();
        }
    }
}

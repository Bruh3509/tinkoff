package edu.hw8.task1;

import java.util.logging.Logger;

public class Client1 {
    private static final Logger LOGGER = Logger.getLogger("Client1");

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws Exception {
        var client1 = new ClientImpl();
        while (true) {
            var response = client1.sendToServer();
            LOGGER.info(String.format("Response to client-%s: %s", client1.getClientId(), response));
        }
    }

    private Client1() {
    }
}

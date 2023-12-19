package edu.hw8.task1;

import java.io.IOException;
import java.util.logging.Logger;

public class Client2 {
    private static final Logger LOGGER = Logger.getLogger("Client2");

    @SuppressWarnings("UncommentedMain")
    public static void main(String[] args) throws IOException {
        var client2 = new ClientImpl();
        while (true) {
            var response = client2.sendToServer();
            LOGGER.info(String.format("Response to client-%s: %s", client2.getClientId(), response));
        }
    }

    private Client2() {
    }
}

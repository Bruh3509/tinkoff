package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;
import java.util.UUID;
import java.util.logging.Logger;
import org.jetbrains.annotations.NotNull;

public class ClientImpl {
    private static final String LOCAL_HOST = "localhost";
    private static final int PORT = 18080;
    private final Logger logger;
    private final UUID clientId;

    public ClientImpl() throws IOException {
        clientId = UUID.randomUUID();
        logger = Logger.getLogger(clientId.toString());
    }

    public String sendToServer() throws IOException {
        logger.info(String.format("Client-%s input: ", clientId.toString()));
        var scanner = new Scanner(System.in);
        var input = scanner.nextLine();

        return getResponse(input);
    }

    public UUID getClientId() {
        return clientId;
    }

    private String getResponse(@NotNull String input) throws IOException {
        try (var client = new Socket(InetAddress.getByName(LOCAL_HOST), PORT)) {
            var outputStream = client.getOutputStream();
            outputStream.write(input.getBytes());
            return new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();
        }
    }
}

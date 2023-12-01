package edu.hw8.task1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.ByteBuffer;

public class MyClient {
    public static void main(String[] args) throws IOException {
        try (Socket client = new Socket(InetAddress.getByName("localhost"), 18080)) {
            var outputStream = client.getOutputStream();
            outputStream.write("личности".getBytes());
            var response = new BufferedReader(new InputStreamReader(client.getInputStream())).readLine();
            System.out.println(response);
        }
    }
}

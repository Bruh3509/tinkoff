package edu.hw6;

import java.io.IOException;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.util.Map;

public class PortTester {
    private static final int MIN_PORT = 1;
    private static final int MAX_PORT = 49151;

    private static final Map<Integer, String> PORTS_PROGRAMS = Map.ofEntries(
        Map.entry(23, "Telnet"),
        Map.entry(110, "POP3"),
        Map.entry(123, "NTP(Network Time Protocol"),
        Map.entry(1024, "Everquest"),
        Map.entry(1029, "Microsoft DCOM"),
        Map.entry(1109, "Kerberized Post Office Protocol"),
        Map.entry(1123, "Murray")
    );

    public static String portsScanner() {
        StringBuilder ports = new StringBuilder();
        for (int port = MIN_PORT; port <= MAX_PORT; ++port) {
            ServerSocket serverSocket = null;
            DatagramSocket datagramSocket = null;
            try {
                serverSocket = new ServerSocket(port);
                serverSocket.setReuseAddress(true);

                datagramSocket = new DatagramSocket(port);
                datagramSocket.setReuseAddress(true);

                ports.append(String.format("%d\tavailable\t%s\n", port, PORTS_PROGRAMS
                    .getOrDefault(port, "")));
            } catch (IOException e) {
                if (serverSocket != null) {
                    try {
                        serverSocket.close();
                    } catch (IOException ignored) {
                    }
                }
                if (datagramSocket != null) {
                    datagramSocket.close();
                }

                ports.append(String.format("%d\tnot available\t%s\n", port, PORTS_PROGRAMS
                    .getOrDefault(port, "")));
            }

        }

        return ports.toString();
    }

    private PortTester() {
    }
}

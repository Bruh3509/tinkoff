package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;

public class MyServer {
    private static final int PORT = 18080;
    private Selector selector;
    private ServerSocketChannel serverSocket;
    private static final ByteBuffer BYTE_BUFFER = ByteBuffer.allocate(256);
    private static final Map<String, String> ANSWERS_STORAGE
        = Map.of(
        "личности", "Не переходи на личности там, где их нет",
        "оскорбления", "Если твои противники перешли на личные оскорбления, будь уверена — твоя победа не за горами",
        "глупый", "А я тебе говорил, что ты глупый? Так вот, я забираю свои слова обратно... Ты просто бог идиотизма",
        "интеллект", "Чем ниже интеллект, тем громче оскорбления"
    );

    public void start() throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", PORT));
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        processing();
    }

    private void processing() throws IOException {
        while (true) {
            selector.select();
            var selectedKeys = selector.selectedKeys();
            var iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                var key = iterator.next();
                if (key.isAcceptable()) {
                    register();
                }
                if (key.isReadable()) {
                    answerToManager(key);
                }
                iterator.remove();
            }
        }
    }

    private void register() throws IOException {
        SocketChannel client = serverSocket.accept();
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
    }

    private void answerToManager(SelectionKey key) throws IOException {
        try (SocketChannel client = (SocketChannel) key.channel()) {
            int bytesRead = client.read(BYTE_BUFFER);
            var managerInput = new StringBuilder();
            if (bytesRead == -1) {
                return;
            }
            BYTE_BUFFER.flip();
            while (BYTE_BUFFER.hasRemaining()) {
                managerInput.append((char) BYTE_BUFFER.get());
            }
            BYTE_BUFFER.clear();

            var tempBuffer = ByteBuffer.allocate(managerInput.length());
            //System.out.println(managerInput.toString()); // TODO
            //if (ANSWERS_STORAGE.containsKey(managerInput.toString())) {
            //tempBuffer.put(ANSWERS_STORAGE.get(managerInput.toString()).getBytes());
            tempBuffer.put("Is it working".getBytes());
            tempBuffer.flip();
            client.write(tempBuffer);
            tempBuffer.clear();
            //}
        }
    }
}

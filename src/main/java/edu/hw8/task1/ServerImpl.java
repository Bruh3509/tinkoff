package edu.hw8.task1;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Map;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;
import java.util.regex.Pattern;

public class ServerImpl {
    private static final int PORT = 18080;
    private static final int MAX_CAPACITY = 2;
    private static final Logger LOGGER = Logger.getLogger("Server Logger");
    private boolean isShutdown = false;
    private Selector selector;
    private ServerSocketChannel serverSocket;
    private final ExecutorService threadPool;
    private final BlockingQueue<SocketChannel> channelBlockingQueue;
    private static final Map<String, String> ANSWERS_STORAGE
        = Map.of(
        "personalities", "Don't get personal where there aren't any",
        "insults", "If your opponents resort to personal insults, rest assured that your victory is not far off",
        "silly", "Did I tell you that you are stupid? So, I take it back... You're just a god of idiocy",
        "intelligence", "The lower the intelligence, the louder the insults"
    );

    public ServerImpl() {
        threadPool = Executors.newFixedThreadPool(MAX_CAPACITY);
        channelBlockingQueue = new LinkedBlockingQueue<>(MAX_CAPACITY);
    }

    public void start() throws IOException {
        selector = Selector.open();
        serverSocket = ServerSocketChannel.open();
        serverSocket.bind(new InetSocketAddress("localhost", PORT), MAX_CAPACITY);
        serverSocket.configureBlocking(false);
        serverSocket.register(selector, SelectionKey.OP_ACCEPT);
        processing();
    }

    public void shutdown() {
        isShutdown = true;
    }

    private void processing() throws IOException {
        while (!isShutdown) {
            selector.select();
            var selectedKeys = selector.selectedKeys();
            var iterator = selectedKeys.iterator();
            while (iterator.hasNext()) {
                var key = iterator.next();
                if (key.isAcceptable()) {
                    register();
                }
                if (key.isReadable()) {
                    threadPool.submit(() -> {
                        try {
                            answerToManager(key);
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
                }
                iterator.remove();
            }
        }
    }

    private void register() throws IOException {
        SocketChannel client = serverSocket.accept();
        channelBlockingQueue.add(client);
        client.configureBlocking(false);
        client.register(selector, SelectionKey.OP_READ);
        LOGGER.info("New connection" + client.socket().getInetAddress());
    }

    private void answerToManager(SelectionKey key) throws IOException {
        try (SocketChannel client = (SocketChannel) key.channel()) {
            var byteBuffer = ByteBuffer.allocate(256);
            int bytesRead = client.read(byteBuffer);
            var managerInput = new StringBuilder();
            if (bytesRead == -1) {
                return;
            }
            byteBuffer.flip();
            while (byteBuffer.hasRemaining()) {
                managerInput.append((char) byteBuffer.get());
            }
            byteBuffer.clear();

            for (var mapKey : ANSWERS_STORAGE.keySet()) {
                if (Pattern.compile(Pattern.quote(mapKey)).matcher(managerInput.toString()).find()) {
                    var tempBuffer = ByteBuffer.allocate(256);
                    tempBuffer.put(ANSWERS_STORAGE.get(mapKey).getBytes());
                    tempBuffer.flip();
                    client.write(tempBuffer);
                    tempBuffer.clear();
                    break;
                }
            }
            channelBlockingQueue.remove(client);
        }
    }
}

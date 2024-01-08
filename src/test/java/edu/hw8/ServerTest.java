package edu.hw8;

import edu.hw8.task1.ClientImpl;
import edu.hw8.task1.ServerImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import static org.assertj.core.api.Assertions.assertThat;

public class ServerTest {
    @Test
    @DisplayName("Test Server Response")
    void testServerResponse() throws IOException, InterruptedException {
        // arrange
        InputStream sysInBackup = System.in;

        var client1 = new ClientImpl();
        String input1 = "Hi silly idiot";
        String input2 = "Hello, nice to meet u (a lot of insults...)";

        // act
        ServerImpl server = new ServerImpl();
        Thread.ofPlatform().start(() -> {
            try {
                server.start();
            } catch (IOException ignored) {
            }
        });
        Thread.sleep(200);

        ByteArrayInputStream in = new ByteArrayInputStream(input1.getBytes());
        System.setIn(in);
        var response1 = client1.sendToServer();

        ByteArrayInputStream in1 = new ByteArrayInputStream(input2.getBytes());
        System.setIn(in1);
        var response2 = client1.sendToServer();

        // assert
        assertThat(response1).isEqualTo(
            "Did I tell you that you are stupid? So, I take it back... You're just a god of idiocy");
        assertThat(response2).isEqualTo(
            "If your opponents resort to personal insults, rest assured that your victory is not far off");
        server.shutdown();
        System.setIn(sysInBackup);
    }
}

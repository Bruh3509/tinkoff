package edu.hw6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PortTesterTest {
    @Test
    @DisplayName("Port Tester Test")
    void testPortTester() {
        var portsInfo = PortTester.portsScanner();
        System.out.println(portsInfo);
    }
}

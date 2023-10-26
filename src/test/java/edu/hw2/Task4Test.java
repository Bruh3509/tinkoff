package edu.hw2;

import static org.assertj.core.api.Assertions.assertThat;
import edu.hw2.task4.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task4Test {
    @Test
    @DisplayName("CallingInfo")
    void testCallingInfo() {
        // arrange

        // act
        CallingInfo callingInfo = WhoIsCalling.callingInfo();

        // assert
        assertThat(callingInfo.methodName()).isEqualTo("testCallingInfo");
        assertThat(callingInfo.className()).isEqualTo("edu.hw2.Task4Test");
    }
}

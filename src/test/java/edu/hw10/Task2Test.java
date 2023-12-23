package edu.hw10;

import edu.hw10.task2.FibCalculator;
import edu.hw10.task2.MyProxy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.lang.reflect.Proxy;
import static org.assertj.core.api.Assertions.assertThat;

public class Task2Test {
    @Test
    @DisplayName("Test Cache")
    void testCache() {
        // arrange
        MyProxy.FibCalc fibCalc = new MyProxy.FibCalc();
        MyProxy.Handler handler = new MyProxy.Handler(fibCalc);
        FibCalculator calc = (FibCalculator) Proxy.newProxyInstance(
            FibCalculator.class.getClassLoader(),
            new Class[] {FibCalculator.class},
            handler
        );

        // act
        var res1 = calc.fib(10);
        var res2 = calc.fib(12);
        var res3 = calc.fib(4);
        var res4 = calc.fib(2);

        // assert
        assertThat(res1).isEqualTo(55);
        assertThat(res2).isEqualTo(144);
        assertThat(res3).isEqualTo(3);
        assertThat(res4).isEqualTo(1);
    }
}

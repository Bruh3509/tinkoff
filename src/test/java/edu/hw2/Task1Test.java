package edu.hw2;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task1Test {
    @Test
    @DisplayName("Fist test")
    void testTask1() {
        // arrange
        var two = new Constant(2);
        var four = new Constant(4);
        var negOne = new Negate(new Constant(1));
        var sumTwoFour = new Addition(two, four);
        var multiplication = new Multiplication(sumTwoFour, negOne);
        var exponent = new Exponent(multiplication, new Constant(2));
        var res = new Addition(exponent, new Constant(1));
        double result;

        // act
        result = res.evaluate();

        //assert
        assertThat(result).isEqualTo(37);
    }
}

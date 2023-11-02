package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.TreeMap;

public class Task7Test {
    @Test
    @DisplayName("Task7 Test Exception")
    void testTask7Exception() {
        // arrange
        TreeMap<String, String> tree = new TreeMap<>();

        // assert
        assertThrows(NullPointerException.class, () -> tree.put(null, "test"));
    }

    @Test
    @DisplayName("Task7 Test Valid")
    void testTask7Valid() {
        // arrange
        TreeMap<String, String> tree = new TreeMap<>(Task7.getNullTreeComparator());

        // act
        tree.put(null, "test");

        // assert
        assertThat(tree.containsKey(null)).isTrue();
    }
}

package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3Test {
    @Test
    @DisplayName("Task3 Test")
    void testTask3() {
        // arrange
        List<String> list1 = List.of("a", "bb", "a", "bb");
        List<String> list2 = List.of("this", "and", "that", "and");
        List<String> list3 = List.of("код", "код", "код", "bug");
        List<Integer> list4 = List.of(1, 1, 2, 2);
        List<Integer> emptyList = List.of();

        // act
        HashMap<String, Integer> resMap1= Task3.frequencyDictionary(list1);
        HashMap<String, Integer> resMap2 = Task3.frequencyDictionary(list2);
        HashMap<String, Integer> resMap3 = Task3.frequencyDictionary(list3);
        HashMap<Integer, Integer> resMap4 = Task3.frequencyDictionary(list4);
        HashMap<Integer, Integer> resEmptyMap = Task3.frequencyDictionary(emptyList);

        // assert
        assertThat(resMap1).isEqualTo(Map.ofEntries(Map.entry("a", 2), Map.entry("bb", 2)));
        assertThat(resMap2).isEqualTo(Map.ofEntries(Map.entry("this", 1), Map.entry("and", 2),
            Map.entry("that", 1)
        ));
        assertThat(resMap3).isEqualTo(Map.ofEntries(Map.entry("код", 3), Map.entry("bug", 1)));
        assertThat(resMap4).isEqualTo(Map.ofEntries(Map.entry(1, 2), Map.entry(2, 2)));
        assertThat(resEmptyMap).isEmpty();
    }
}

package edu.hw3;

import java.util.HashMap;
import java.util.List;

public class Task3 {
    public static <T> HashMap<T, Integer> frequencyDictionary(List<T> inputList) {
        HashMap<T, Integer> resultMap = new HashMap<>();

        for (T element : inputList) {
            if (resultMap.containsKey(element)) {
                resultMap.put(element, resultMap.get(element) + 1);
            } else {
                resultMap.put(element, 1);
            }
        }

        return resultMap;
    }
}

package edu.hw3;

import java.util.Comparator;

public class Task7 {
    public static <T extends Comparable<T>> Comparator<T> getNullTreeComparator() {

        return (T node1, T node2) -> {
            if (node1 == null && node2 == null) {
                return 0;
            } else if (node1 == null) {
                return -1;
            } else if (node2 == null) {
                return 1;
            } else {
                return node1.compareTo(node2);
            }
        };
    }

    private Task7() {
    }
}

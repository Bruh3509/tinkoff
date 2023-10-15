package edu.hw1;

import java.util.Arrays;

public final class Task3 {
    @SuppressWarnings({"OptionalGetWithoutIsPresent", "MagicNumber"})
    public static boolean isNestable(int[] arr1, int[] arr2) {
        return (Arrays.stream(arr1).min().getAsInt() > Arrays.stream(arr2).min().getAsInt())
            && (Arrays.stream(arr1).max().getAsInt() < Arrays.stream(arr2).max().getAsInt());
    }

    private Task3() {
    }
}

package edu.hw3;

import java.util.Arrays;

public class Task1 {
    private static final char[] ALPHABET =
        {'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i',
            'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v',
            'w', 'x', 'y', 'z'};

    public static String atbash(String string) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            int position = Arrays.binarySearch(ALPHABET, Character.toLowerCase(string.charAt(i)));
            if (position < 0) {
                resultString.append(string.charAt(i));
            } else {
                char character = ALPHABET[ALPHABET.length - position - 1];
                if (Character.isUpperCase(string.charAt(i))) {
                    resultString.append(Character.toUpperCase(character));
                } else {
                    resultString.append(character);
                }
            }
        }

        return resultString.toString();
    }

    private Task1() {
    }
}

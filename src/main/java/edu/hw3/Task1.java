package edu.hw3;

import java.util.Map;

public class Task1 {
    private static final Map<Character, Character> CIPHER = Map.ofEntries(
        Map.entry('a', 'z'),
        Map.entry('b', 'y'),
        Map.entry('c', 'x'),
        Map.entry('d', 'w'),
        Map.entry('e', 'v'),
        Map.entry('f', 'u'),
        Map.entry('g', 't'),
        Map.entry('h', 's'),
        Map.entry('i', 'r'),
        Map.entry('j', 'q'),
        Map.entry('k', 'p'),
        Map.entry('l', 'o'),
        Map.entry('m', 'n'),
        Map.entry('n', 'm'),
        Map.entry('o', 'l'),
        Map.entry('p', 'k'),
        Map.entry('q', 'j'),
        Map.entry('r', 'i'),
        Map.entry('s', 'h'),
        Map.entry('t', 'g'),
        Map.entry('u', 'f'),
        Map.entry('v', 'e'),
        Map.entry('w', 'd'),
        Map.entry('x', 'c'),
        Map.entry('y', 'b'),
        Map.entry('z', 'a')
    );

    public static String atbash(String string) {
        StringBuilder resultString = new StringBuilder();
        for (int i = 0; i < string.length(); ++i) {
            char lowerCaseChar = Character.toLowerCase(string.charAt(i));
            if (!CIPHER.containsKey(lowerCaseChar)) {
                resultString.append(string.charAt(i));
            } else {
                char character = CIPHER.get(lowerCaseChar);
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

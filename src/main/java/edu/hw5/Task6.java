package edu.hw5;

import java.util.regex.Pattern;

public class Task6 {
    public static boolean isSubstring(String substring, String string) {
        return Pattern.compile(Pattern.quote(substring))
            .matcher(string)
            .find();
    }

    private Task6() {
    }
}

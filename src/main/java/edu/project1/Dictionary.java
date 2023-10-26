package edu.project1;

import java.util.Random;

public class Dictionary {
    private static final String[] WORDS = new String[] {
        "cheese", "word", "mom"
    };

    public static String randomWord() {
        Random random = new Random();
        return WORDS[Math.abs(random.nextInt()) % WORDS.length];
    }

    private Dictionary() {
    }
}

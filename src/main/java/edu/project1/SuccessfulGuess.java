package edu.project1;

public record SuccessfulGuess(String curWord, int curAttempts) implements GuessResult {
    @Override
    public String state() {
        return String.format("Hit!\nThe word: %s\n", curWord);
    }

    @Override
    public int attempts() {
        return curAttempts;
    }
}

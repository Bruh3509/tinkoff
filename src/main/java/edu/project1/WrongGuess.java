package edu.project1;

public record WrongGuess(String curWord, int curAttempts, int maxAttempts) implements GuessResult {
    @Override
    public String state() {
        return String.format("Missed, mistake %d of %d.\nThe word: %s",
            curAttempts, maxAttempts, curWord
        );
    }

    @Override
    public int attempts() {
        return curAttempts;
    }
}

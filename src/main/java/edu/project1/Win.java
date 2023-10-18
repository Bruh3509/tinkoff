package edu.project1;

public record Win(String answer, int curAttempts) implements GuessResult {
    @Override
    public String state() {
        return String.format("Hit!\nThe word: %s\nYou won!\n", answer);
    }

    @Override
    public int attempts() {
        return curAttempts;
    }
}

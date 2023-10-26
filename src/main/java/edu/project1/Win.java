package edu.project1;

public record Win(String answer, int curMistakes) implements GuessResult {
    @Override
    public String state() {
        return String.format("Hit!\nThe word: %s\nYou won!\n", answer);
    }
}

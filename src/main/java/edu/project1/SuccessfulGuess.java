package edu.project1;

public record SuccessfulGuess(String curWord, int curMistakes) implements GuessResult {
    @Override
    public String state() {
        return String.format("Hit!\nThe word: %s\n", curWord);
    }

    @Override
    public int mistakes() {
        return curMistakes;
    }
}

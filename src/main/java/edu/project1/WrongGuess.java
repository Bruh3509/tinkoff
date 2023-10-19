package edu.project1;

public record WrongGuess(String curWord, int curMistakes, int maxMistakes) implements GuessResult {
    @Override
    public String state() {
        return String.format("Missed, mistake %d of %d.\nThe word: %s\n",
            curMistakes, maxMistakes, curWord
        );
    }
}

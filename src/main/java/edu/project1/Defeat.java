package edu.project1;

public record Defeat(String curWord, int maxMistakes) implements GuessResult {
    @Override
    public String state() {
        return String.format("Missed, mistake %d of %d.\nThe word: %s\nYou lost!\n",
            maxMistakes, maxMistakes, curWord
        );
    }

    @Override
    public int mistakes() {
        return maxMistakes;
    }
}

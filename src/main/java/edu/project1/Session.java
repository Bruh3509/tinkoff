package edu.project1;

import java.util.Arrays;
import java.util.SequencedSet;

public class Session {
    private final String ANSWER;
    private final int MAX_MISTAKES;
    private char[] userAnswer;
    private int curMistakes;

    public Session(String answer, int maxMistakes) {
        this.ANSWER = answer;
        this.MAX_MISTAKES = maxMistakes;

        curMistakes = 0;
        userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
    }

    public GuessResult guess(char c) {
        for (int i = 0; i < ANSWER.length(); ++i) {
            if (ANSWER.charAt(i) == c) {
                userAnswer[i] = c;
                if (new String(userAnswer).contains("*")) {
                    return new SuccessfulGuess(new String(userAnswer), curMistakes);
                }
                return new Win(new String(userAnswer), curMistakes);
            }
        }
        if (++curMistakes == MAX_MISTAKES) {
            return new Defeat(new String(userAnswer), curMistakes);
        }
        return new WrongGuess(new String(userAnswer), curMistakes, MAX_MISTAKES);
    }

    public GuessResult giveUp() {
        return new Defeat(new String(userAnswer), curMistakes);
    }
}

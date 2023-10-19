package edu.project1;

import java.util.Arrays;

public class Session {
    private final String answer;
    private final int maxMistakes;
    private final char[] userAnswer;
    private int curMistakes;

    public Session(String answer, int maxMistakes) {
        if (answer == null || answer.isEmpty()) {
            throw new NotValidStringException();
        }
        this.answer = answer;
        this.maxMistakes = maxMistakes;

        curMistakes = 0;
        userAnswer = new char[answer.length()];
        Arrays.fill(userAnswer, '*');
    }

    public GuessResult guess(char c) {
        if (isHit(c)) {
            if (new String(userAnswer).contains("*")) {
                return new SuccessfulGuess(new String(userAnswer), curMistakes);
            } else {
                return new Win(new String(userAnswer), curMistakes);
            }
        }
        if (++curMistakes == maxMistakes) {
            return new Defeat(new String(userAnswer), curMistakes);
        }
        return new WrongGuess(new String(userAnswer), curMistakes, maxMistakes);
    }

    public GuessResult giveUp() {
        return new Defeat(new String(userAnswer), curMistakes);
    }

    public String getUserAnswer() {
        return new String(userAnswer);
    }

    public int getCurMistakes() {
        return curMistakes;
    }

    private boolean isHit(char c) {
        boolean isGuessed = false;
        for (int i = 0; i < answer.length(); ++i) {
            if (answer.charAt(i) == c) {
                userAnswer[i] = c;
                isGuessed = true;
            }
        }
        return isGuessed;
    }
}

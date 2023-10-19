package edu.project1;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import java.util.ArrayList;

public class Project1Test {
    private static final String ANSWER = "smth";
    private static final String CUR_WORD = "s*t*";
    private static final int MAX_MISTAKES = 5;

    static Arguments[] results() {
        return new Arguments[] {
            Arguments.of(new Defeat(CUR_WORD, 0)),
            Arguments.of(new Win(CUR_WORD, 0)),
            Arguments.of(new SuccessfulGuess(CUR_WORD, 0)),
            Arguments.of(new WrongGuess(CUR_WORD, 0, 5))
        };
    }

    @Test
    @DisplayName("Correct Length")
    void testWordLength() {
        // arrange
        String userInput1 = "";
        String userInput2 = null;

        // act

        // assert
        assertThrows(NotValidStringException.class, () -> new Session(userInput1, MAX_MISTAKES));
        assertThrows(NotValidStringException.class, () -> new Session(userInput2, MAX_MISTAKES));
    }

    @Test
    @DisplayName("Give up")
    void testGiveUp() {
        // arrange
        Session session = new Session(ANSWER, MAX_MISTAKES);
        ConsoleHangman hangman = new ConsoleHangman(session, System.in);
        String userInput = "CONCIDE";

        // act
        GuessResult returnResult = hangman.tryGuess(userInput, null);

        // assert
        assertThat(returnResult.getClass()).isEqualTo(Defeat.class);
    }

    @ParameterizedTest
    @DisplayName("Input Typo")
    @MethodSource("results")
    void testInputTypo(GuessResult result) {
        // arrange
        Session session = new Session(ANSWER, MAX_MISTAKES);
        ConsoleHangman hangman = new ConsoleHangman(session, System.in);
        String userInput = "someString";
        String userAnswer = session.getUserAnswer();
        int curMistakes = session.getCurMistakes();

        // act
        GuessResult returnResult = hangman.tryGuess(userInput, result);
        String resCurAnswer = session.getUserAnswer();
        int resCurMistakes = session.getCurMistakes();

        // assert
        assertThat(returnResult.getClass()).isEqualTo(result.getClass());
        assertThat(resCurAnswer).isEqualTo(userAnswer);
        assertThat(resCurMistakes).isEqualTo(curMistakes);
    }

    @Test
    @DisplayName("Correct State Change Success Guess")
    void testSuccessStateChange() {
        // arrange
        Session session = new Session(ANSWER, MAX_MISTAKES);
        ConsoleHangman hangman = new ConsoleHangman(session, System.in);
        String userAnswer = session.getUserAnswer();
        int curMistakes = session.getCurMistakes();
        String guess = "s";

        // act
        GuessResult returnResult1 = hangman.tryGuess(guess, new SuccessfulGuess("", 0));
        String resUserAnswer = session.getUserAnswer();
        int resCurMistakes = session.getCurMistakes();

        // assert
        assertEquals(returnResult1.getClass(), SuccessfulGuess.class);
        assertThat(curMistakes).isEqualTo(resCurMistakes);
        assertThat(userAnswer).isNotEqualTo(resUserAnswer);
        assertThat(userAnswer).doesNotContain(guess);

    }

    @Test
    @DisplayName("Correct State Change Wrong Guess")
    void testWrongStateChange() {
        // arrange
        Session session = new Session(ANSWER, MAX_MISTAKES);
        ConsoleHangman hangman = new ConsoleHangman(session, System.in);
        String userAnswer = session.getUserAnswer();
        int curMistakes = session.getCurMistakes();
        String guess = "a";

        // act
        GuessResult returnResult = hangman.tryGuess(guess, new SuccessfulGuess("", 0));
        String resUserAnswer = session.getUserAnswer();
        int resCurMistakes = session.getCurMistakes();

        // assert
        assertEquals(returnResult.getClass(), WrongGuess.class);
        assertThat(curMistakes + 1).isEqualTo(resCurMistakes);
        assertThat(userAnswer).isEqualTo(resUserAnswer);
    }
}

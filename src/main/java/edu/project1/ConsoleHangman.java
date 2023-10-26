package edu.project1;

import java.io.InputStream;
import java.util.Scanner;
import java.util.logging.Logger;

public class ConsoleHangman {
    private static final Logger LOGGER = Logger.getLogger("Hangman Logger");
    private static final String CONCIDE_COMMAND = "CONCIDE";
    private final Session session;
    private final Scanner input;

    public ConsoleHangman(Session session, InputStream input) {
        this.session = session;
        this.input = new Scanner(input);
    }

    public void run() {
        GuessResult curResult = new SuccessfulGuess("", 0);
        do {
            LOGGER.info("Guess the letter: \n");
            String nextInput = input.next();
            curResult = tryGuess(nextInput, curResult);
        } while (!curResult.getClass().equals(Win.class) && !curResult.getClass().equals(Defeat.class));
    }

    GuessResult tryGuess(String input, GuessResult curGuessResult) {
        GuessResult returnResult = curGuessResult;
        if (input.equals(CONCIDE_COMMAND)) {
            returnResult = session.giveUp();
        } else if (input.length() == 1) {
            returnResult = session.guess(input.charAt(0));
            LOGGER.info(returnResult.state());
        }
        return returnResult;
    }
}

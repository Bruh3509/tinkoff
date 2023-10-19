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
            String nextInput = input.next();
            if (nextInput.equals(CONCIDE_COMMAND)) {
                curResult = session.giveUp();
            } else if (nextInput.length() == 1) {
                curResult = session.guess(nextInput.charAt(0));
                LOGGER.info(curResult.state());
            }
        } while (curResult.getClass() != Win.class && curResult.getClass() != Defeat.class);
    }
}

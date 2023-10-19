package edu.project1;

import java.util.Scanner;

public class ConsoleHangman {
    private final Session session;
    private final Scanner input;

    public ConsoleHangman(Session session, Scanner input) {
        this.session = session;
        this.input = input;
    }

    public void run() {
        GuessResult curResult;
        do {
            curResult = tryToGuess(input.next().charAt(0));
            System.out.println(curResult.state());
        } while (curResult.getClass() != Win.class && curResult.getClass() != Defeat.class);
    }

    private GuessResult tryToGuess(char c) {
        return session.guess(c);
    }
}

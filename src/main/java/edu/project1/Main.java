package edu.project1;

public class Main {
    @SuppressWarnings("MagicNumber")
    public static void main(String[] args) {
        Session session = new Session(Dictionary.randomWord(), 5);
        ConsoleHangman consoleHangman = new ConsoleHangman(session, System.in);
        consoleHangman.run();
    }

    private Main() {
    }
}

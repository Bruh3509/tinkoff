package edu.project1;

public class Main {
    public static void main(String[] args) {
        Session session = new Session("cheese", 5);
        ConsoleHangman consoleHangman = new ConsoleHangman(session, System.in);
        consoleHangman.run();
    }

    private Main() {
    }
}

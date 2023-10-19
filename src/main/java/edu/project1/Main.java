package edu.project1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Session session = new Session("help", 5);
        ConsoleHangman consoleHangman = new ConsoleHangman(session, new Scanner(System.in));
        consoleHangman.run();
    }
}

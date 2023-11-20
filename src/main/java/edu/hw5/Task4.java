package edu.hw5;

public class Task4 {
    public static boolean isSecurePassword(String password) {
        return password.matches(".*[~!@#$%^&*|].*");
    }

    private Task4() {
    }
}

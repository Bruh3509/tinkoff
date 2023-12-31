package edu.hw1;

public final class Task4 {
    @SuppressWarnings("MagicNumber")
    public static String fixString(String brokenString) {
        StringBuilder res = new StringBuilder();

        for (int i = 1; i < brokenString.length(); i += 2) {
            res.append(brokenString.charAt(i));
            res.append(brokenString.charAt(i - 1));
        }

        if (res.length() != brokenString.length()) {
            res.append(brokenString.charAt(brokenString.length() - 1));
        }

        return res.toString();
    }

    private Task4() {
    }
}

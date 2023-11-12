package edu.hw5;

public class Task7 {
    public static boolean task1(String string) {
        return string.matches("[01]{2}0[01]*");
    }

    public static boolean task2(String string) {
        return (string.matches("0[01]*0")
            || string.matches("1[01]*1"))
            || string.matches("1")
            || string.matches("0");
    }

    public static boolean task3(String string) {
        return string.matches("[01]{1,3}");
    }

    private Task7() {
    }
}

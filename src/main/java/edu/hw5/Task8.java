package edu.hw5;

public class Task8 {
    // odd length
    public static boolean task1(String string) {
        return string.matches("([01]{2})*[01]");
    }

    // quantity 0 is a multiple of 3
    public static boolean task2(String string) {
        return string.matches("1*0(1|01*01*0)*(01*01*)");
    }

    // any string other than 11 or 111
    public static boolean task3(String string) {
        return string.matches("1|(0|10|110|(111[01]+))[01]*");
    }

    // every odd character is 1
    public static boolean task4(String string) {
        return string.matches("(1[01])*1?");
    }

    // no consecutive 1
    public static boolean task5(String string) {
        return string.matches("(0|10)*1?");
    }

    private Task8() {
    }
}

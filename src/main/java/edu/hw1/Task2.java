package edu.hw1;

public final class Task2 {
    @SuppressWarnings("MagicNumber")
    public static int countDigits(int num) {
        if (num == 0) {
            return 1;
        }

        int res = 0;
        int absNum = Math.abs(num);

        while (absNum > 0) {
            ++res;
            absNum /= 10;
        }

        return res;
    }

    private Task2() {
    }
}

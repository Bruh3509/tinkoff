package edu.hw1;

public final class Task7 {
    public static int rotateRight(int num, int shift) {
        char[] binNum = Integer.toBinaryString(num).toCharArray();

        for (int i = 0; i < shift; ++i) {
            char temp = binNum[0];
            char last = binNum[binNum.length - 1];

            for (int j = 1; j < binNum.length; ++j) {
                char temp1 = binNum[j];
                binNum[j] = temp;
                temp = temp1;
            }

            binNum[0] = last;
        }

        return Integer.parseInt(new String(binNum), 2);
    }

    public static int rotateLeft(int num, int shift) {
        char[] binNum = Integer.toBinaryString(num).toCharArray();

        for (int i = 0; i < shift; ++i) {
            char temp = binNum[binNum.length - 1];
            char first = binNum[0];

            for (int j = binNum.length - 2; j >= 0; --j) {
                char temp1 = binNum[j];
                binNum[j] = temp;
                temp = temp1;
            }

            binNum[binNum.length - 1] = first;
        }

        return Integer.parseInt(new String(binNum), 2);
    }

    private Task7() {
    }
}

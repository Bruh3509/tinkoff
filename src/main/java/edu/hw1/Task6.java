package edu.hw1;

import java.util.Arrays;

public final class Task6 {

    @SuppressWarnings({"MagicNumber", "ParameterAssignment"})
    public static int countKaprekars(int num, int res) {
        if (num == 1000 || String.valueOf(num).length() != 4) {
            return -1;
        }
        if (num != 6174) {
            int increaseNum;
            int decreaseNum;
            char[] sortedStr = String.valueOf(num).toCharArray();
            Arrays.sort(sortedStr);
            StringBuilder stringBuilder = new StringBuilder(new String(sortedStr));
            increaseNum = Integer.parseInt(stringBuilder.toString());
            decreaseNum = Integer.parseInt(stringBuilder.reverse().toString());
            return countKaprekars(Math.abs(decreaseNum - increaseNum), ++res);
        } else {
            return res;
        }
    }

    private Task6() {
    }
}

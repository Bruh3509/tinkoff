package edu.hw1;

public final class Task5 {
    @SuppressWarnings("MagicNumber")
    public static boolean isPalindromeDescendant(int num) {
        String localStrNum = String.valueOf(num);
        while (localStrNum.length() > 1) {
            StringBuilder curStrNum = new StringBuilder(localStrNum);
            if (curStrNum.reverse().toString().equals(localStrNum)) {
                return true;
            }
            if (localStrNum.length() % 2 != 0) {
                return false;
            }

            int curNum = Integer.parseInt(curStrNum.toString());
            curStrNum = new StringBuilder();
            while (curNum > 0) {
                curStrNum.append(curNum % 10 + (curNum / 10) % 10);
                curNum /= 100;
            }
            localStrNum = curStrNum.toString();
        }

        return false;
    }

    private Task5() {
    }
}

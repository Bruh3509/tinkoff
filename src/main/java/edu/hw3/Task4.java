package edu.hw3;

public class Task4 {
    private static final char[] ROMAN_NUMBERS = {'M', 'D', 'C', 'L', 'X', 'V', 'I'};
    private static final int[] DIVISION_CONSTANTS = {1000, 500, 100, 50, 10, 5, 1};

    public static String convertToRoman(int arabicNumber) {
        int localArabicNumber = arabicNumber;
        StringBuilder romanNumber = new StringBuilder();

        for (int i = 0; i < ROMAN_NUMBERS.length; ++i) {
            romanNumber.append(String.valueOf(ROMAN_NUMBERS[i])
                .repeat(Math.max(0, localArabicNumber / DIVISION_CONSTANTS[i])));
            localArabicNumber %= DIVISION_CONSTANTS[i];
        }
        return romanNumber.toString();
    }

    private Task4() {
    }
}

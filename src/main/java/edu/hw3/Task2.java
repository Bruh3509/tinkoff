package edu.hw3;

import java.util.ArrayList;
import java.util.InputMismatchException;

public class Task2 {

    public static ArrayList<String> clusterize(String string) {
        if (string.charAt(0) != '(') {
            throw new InputMismatchException();
        }

        ArrayList<String> resArray = new ArrayList<>();
        int beginIndex = 0;
        int counter = 0;
        for (int i = 0; i < string.length(); ++i) {
            if (string.charAt(i) == '(') {
                ++counter;
            } else if (string.charAt(i) == ')') {
                --counter;
            } else {
                throw new InputMismatchException();
            }

            if (counter == 0) {
                resArray.add(string.substring(beginIndex, i + 1));
                beginIndex = i + 1;
            }

            if (counter < 0) {
                throw new InputMismatchException();
            }
        }

        return resArray;
    }

    private Task2() {
    }
}

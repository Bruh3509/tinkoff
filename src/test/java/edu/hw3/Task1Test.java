package edu.hw3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class Task1Test {
    @Test
    @DisplayName("Task1 Test")
    void testTask1() {
        // arrange
        String string1 = "Hello world!";
        String string2 = "Any fool can write code that a computer can understand." +
            " Good programmers write code that humans can understand. ― Martin Fowler";
        String emptyString = "";
        String noLatinAlphabet = "Привет, мир!";

        // act
        String resString1 = Task1.atbash(string1);
        String resString2 = Task1.atbash(string2);
        String resEmptyString = Task1.atbash(emptyString);
        String resNoLatinAlphabet = Task1.atbash(noLatinAlphabet);

        // assert
        assertEquals(resString1, "Svool dliow!");
        assertEquals(resString2, "Zmb ullo xzm dirgv xlwv gszg z xlnkfgvi xzm fmwvihgzmw." +
            " Tllw kiltiznnvih dirgv xlwv gszg sfnzmh xzm fmwvihgzmw. ― Nzigrm Uldovi");
        assertEquals(resEmptyString, "");
        assertEquals(resNoLatinAlphabet, "Привет, мир!");
    }
}

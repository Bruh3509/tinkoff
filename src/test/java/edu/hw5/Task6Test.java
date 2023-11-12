package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task6Test {
    @Test
    @DisplayName("Task6 Test")
    void testTask6() {
        // arrange
        var string1 = "Hello, world!";
        var string2 = "My Name is ...";
        var string3 = "There is some text";

        var substring1 = "Hello";
        var substring2 = "hello";
        var substring3 = "Name";
        var substring4 = "is...";
        var substring5 = "text";
        var substring6 = "is some";

        // act
        var substringRes1 = Task6.isSubstring(substring1, string1);
        var substringRes2 = Task6.isSubstring(substring2, string1);
        var substringRes3 = Task6.isSubstring(substring3, string2);
        var substringRes4 = Task6.isSubstring(substring4, string2);
        var substringRes5 = Task6.isSubstring(substring5, string3);
        var substringRes6 = Task6.isSubstring(substring6, string3);

        // assert
        assertTrue(substringRes1);
        assertFalse(substringRes2);

        assertTrue(substringRes3);
        assertFalse(substringRes4);

        assertTrue(substringRes5);
        assertTrue(substringRes6);
    }
}

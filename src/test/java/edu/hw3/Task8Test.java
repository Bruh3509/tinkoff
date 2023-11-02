package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class Task8Test {
    private static final List<Integer> LIST = List.of(1, 2, 3);

    @Test
    @DisplayName("Task8 Test Valid next")
    void testTask8ValidNext() {
        // arrange
        Iterator<Integer> backwardIterator = new BackwardIterator<>(LIST);

        // act
        Integer thirdElement = backwardIterator.next();
        Integer secondElement = backwardIterator.next();
        Integer firstElement = backwardIterator.next();

        // assert
        assertThat(thirdElement).isEqualTo(3);
        assertThat(secondElement).isEqualTo(2);
        assertThat(firstElement).isEqualTo(1);
    }

    @Test
    @DisplayName("Task8 Test NotValid next")
    void testTask8NotValidNext() {
        // arrange
        Iterator<Integer> backwardIterator = new BackwardIterator<>(LIST);

        // act
        backwardIterator.next();
        backwardIterator.next();
        backwardIterator.next();

        // assert
        assertThrows(NoSuchElementException.class, backwardIterator::next);
    }

    @Test
    @DisplayName("Task8 Test hasNext")
    void testTask8HasNext() {
        // arrange
        Iterator<Integer> backwardIterator = new BackwardIterator<>(LIST);

        // act
        backwardIterator.next();
        boolean hasNext1 = backwardIterator.hasNext();
        backwardIterator.next();
        boolean hasNext2 = backwardIterator.hasNext();
        backwardIterator.next();
        boolean hasNext3 = backwardIterator.hasNext();

        // assert
        assertTrue(hasNext1);
        assertTrue(hasNext2);
        assertFalse(hasNext3);
    }
}

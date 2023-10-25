package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class Task5Test {
    @Test
    @DisplayName("Task5 Test")
    void testTask5() {
        // arrange
        String[] contacts1 = {"John Locke", "Thomas Aquinas", "David Hume", "Rene Descartes"};
        String contacts1Sort = "ASC";
        String[] contacts2 = {"Paul Erdos", "Leonhard Euler", "Carl Gauss"};
        String contacts2Sort = "DESC";
        String[] contacts3 = {};
        String contacts3Sort1 = "ASC";
        String contacts3Sort2 = "DESC";
        String[] contacts4 = {"Paul", "Leonardo Euler", "Carl"};
        String contacts4Sort = "ASC";

        // act
        String[] resultContacts1 = Task5.parseContacts(contacts1, contacts1Sort);
        String[] resultContacts2 = Task5.parseContacts(contacts2, contacts2Sort);
        String[] resultContacts3Asc = Task5.parseContacts(contacts3, contacts3Sort1);
        String[] resultContacts3Desc = Task5.parseContacts(contacts3, contacts3Sort2);
        String[] resultContacts4 = Task5.parseContacts(contacts4, contacts4Sort);

        // assert
        assertThat(resultContacts1).isEqualTo(new String[] {"Thomas Aquinas", "Rene Descartes", "David Hume",
            "John Locke"});
        assertThat(resultContacts2).isEqualTo(new String[] {"Carl Gauss", "Leonhard Euler", "Paul Erdos"});
        assertThat(resultContacts3Asc).isEmpty();
        assertThat(resultContacts3Desc).isEmpty();
        assertThat(resultContacts4).isEqualTo(new String[] {"Carl", "Leonardo Euler", "Paul"});
    }
}

package edu.hw3;

import static org.assertj.core.api.Assertions.*;
import edu.hw3.Task5.Contact;
import edu.hw3.Task5.Task5;
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
        final var resultContacts1 = Task5.parseContacts(contacts1, contacts1Sort);
        final var resultContacts2 = Task5.parseContacts(contacts2, contacts2Sort);
        final var resultContacts3Asc = Task5.parseContacts(contacts3, contacts3Sort1);
        final var resultContacts3Desc = Task5.parseContacts(contacts3, contacts3Sort2);
        final var resultContacts4 = Task5.parseContacts(contacts4, contacts4Sort);

        // assert
        assertThat(resultContacts1).isEqualTo(new Contact[] {
            new Contact("Thomas", "Aquinas"),
            new Contact("Rene", "Descartes"),
            new Contact("David", "Hume"),
            new Contact("John", "Locke")
        });
        assertThat(resultContacts2).isEqualTo(new Contact[] {
            new Contact("Carl", "Gauss"),
            new Contact("Leonhard", "Euler"),
            new Contact("Paul", "Erdos")
        });
        assertThat(resultContacts3Asc).isEmpty();
        assertThat(resultContacts3Desc).isEmpty();
        assertThat(resultContacts4).isEqualTo(new Contact[] {
            new Contact("Carl", ""),
            new Contact("Leonardo", "Euler"),
            new Contact("Paul", "")
        });
    }
}

package edu.hw5;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class Task3Test {
    @Test
    @DisplayName("Task3 Test")
    void testTask3ParseDate() {
        // arrange
        var date1 = "2020-10-10";
        var date2 = "2020-12-2";
        var date3 = "1/3/1976";
        var date4 = "1/3/20";
        var date5 = "tomorrow";
        var date6 = "today";
        var date7 = "yesterday";
        var date8 = "1 day ago";
        var date9 = "13 days ago";

        // act
        var localDate1 = Task3.parseDate(date1);
        var localDate2 = Task3.parseDate(date2);
        var localDate3 = Task3.parseDate(date3);
        var localDate4 = Task3.parseDate(date4);
        var localDate5 = Task3.parseDate(date5);
        var localDate6 = Task3.parseDate(date6);
        var localDate7 = Task3.parseDate(date7);
        var localDate8 = Task3.parseDate(date8);
        var localDate9 = Task3.parseDate(date9);

        // assert
        assertTrue(localDate1.isPresent());
        assertThat(localDate1.get()).isEqualTo(LocalDate.of(2020, 10, 10));

        assertTrue(localDate2.isPresent());
        assertThat(localDate2.get()).isEqualTo(LocalDate.of(2020, 12, 2));

        assertTrue(localDate3.isPresent());
        assertThat(localDate3.get()).isEqualTo(LocalDate.of(1976, 3, 1));

        assertTrue(localDate4.isPresent());
        assertThat(localDate4.get()).isEqualTo(LocalDate.of(2020, 3, 1));

        assertTrue(localDate5.isPresent());
        assertThat(localDate5.get()).isEqualTo(LocalDate.now().plusDays(1));

        assertTrue(localDate6.isPresent());
        assertThat(localDate6.get()).isEqualTo(LocalDate.now());

        assertTrue(localDate7.isPresent());
        assertThat(localDate7.get()).isEqualTo(LocalDate.now().minusDays(1));

        assertTrue(localDate8.isPresent());
        assertThat(localDate8.get()).isEqualTo(LocalDate.now().minusDays(1));

        assertTrue(localDate9.isPresent());
        assertThat(localDate9.get()).isEqualTo(LocalDate.now().minusDays(13));
    }
}

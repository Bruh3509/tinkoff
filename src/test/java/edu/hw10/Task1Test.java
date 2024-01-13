package edu.hw10;

import edu.hw10.task1.MyClass;
import edu.hw10.task1.MyRecord;
import edu.hw10.task1.RandomObjectGenerator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class Task1Test {
    @Test
    @DisplayName("Test Annotations MyClass")
    void testAnnotationsClass() throws Throwable {
        // arrange
        var rand = new RandomObjectGenerator();

        // act
        var myClass = (MyClass) rand.nextObject(MyClass.class);
        var myClassFabric = (MyClass) rand.nextObject(MyClass.class, "create");

        // assert
        assertThat(myClass.getAge()).isBetween(70, 100);
        assertThat(myClass.getName()).isNotNull();

        assertThat(myClassFabric.getAge()).isBetween(0, 50);
        assertThat(myClassFabric.getName()).isNotNull();
    }

    @Test
    @DisplayName("Test Annotations MyRecord")
    void testAnnotationsRecord() {
        // arrange
        var rand = new RandomObjectGenerator();

        // act
        var myRecord = (MyRecord) rand.nextObject(MyRecord.class);

        // assert
        assertThat(myRecord.age()).isBetween(0, 20);
    }
}

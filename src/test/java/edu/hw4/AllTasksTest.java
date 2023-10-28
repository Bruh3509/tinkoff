package edu.hw4;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class AllTasksTest {
    private static final List<Animal> ANIMALS = List.of(
        new Animal("Alex", Animal.Type.CAT, Animal.Sex.M, 13, 30, 15, true),
        new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, 5, 10, 5, true),
        new Animal("Dori", Animal.Type.FISH, Animal.Sex.F, 3, 5, 7, false),
        new Animal("Shnyuk", Animal.Type.SPIDER, Animal.Sex.M, 15, 40, 30, false),
        new Animal("Bars", Animal.Type.DOG, Animal.Sex.M, 6, 20, 17, false),
        new Animal("Strelka", Animal.Type.DOG, Animal.Sex.F, 4, 21, 22, true),
        new Animal("Belka", Animal.Type.DOG, Animal.Sex.F, 4, 19, 21, true)
    );

    @Test
    @DisplayName("Task1 Test")
    void testTask1() {
        // act
        final var sortedAnimals = AllTasks.task1(ANIMALS);

        // assert
        assertThat(sortedAnimals).containsExactly(
            new Animal("Dori", Animal.Type.FISH, Animal.Sex.F, 3, 5, 7, false),
            new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, 5, 10, 5, true),
            new Animal("Belka", Animal.Type.DOG, Animal.Sex.F, 4, 19, 21, true),
            new Animal("Bars", Animal.Type.DOG, Animal.Sex.M, 6, 20, 17, false),
            new Animal("Strelka", Animal.Type.DOG, Animal.Sex.F, 4, 21, 22, true),
            new Animal("Alex", Animal.Type.CAT, Animal.Sex.M, 13, 30, 15, true),
            new Animal("Shnyuk", Animal.Type.SPIDER, Animal.Sex.M, 15, 40, 30, false)
        );
    }

    @Test
    @DisplayName("Task2 Test")
    void testTask2() {
        // arrange
        final int k1 = 5;
        final int k2 = 10;

        // act
        final var sortedAnimals1 = AllTasks.task2(ANIMALS, k1);
        final var sortedAnimals2 = AllTasks.task2(ANIMALS, k2);

        // assert
        assertThat(sortedAnimals1).containsExactly(
            new Animal("Shnyuk", Animal.Type.SPIDER, Animal.Sex.M, 15, 40, 30, false),
            new Animal("Strelka", Animal.Type.DOG, Animal.Sex.F, 4, 21, 22, true),
            new Animal("Belka", Animal.Type.DOG, Animal.Sex.F, 4, 19, 21, true),
            new Animal("Bars", Animal.Type.DOG, Animal.Sex.M, 6, 20, 17, false),
            new Animal("Alex", Animal.Type.CAT, Animal.Sex.M, 13, 30, 15, true)
        );

        assertThat(sortedAnimals2).containsExactly(
            new Animal("Shnyuk", Animal.Type.SPIDER, Animal.Sex.M, 15, 40, 30, false),
            new Animal("Strelka", Animal.Type.DOG, Animal.Sex.F, 4, 21, 22, true),
            new Animal("Belka", Animal.Type.DOG, Animal.Sex.F, 4, 19, 21, true),
            new Animal("Bars", Animal.Type.DOG, Animal.Sex.M, 6, 20, 17, false),
            new Animal("Alex", Animal.Type.CAT, Animal.Sex.M, 13, 30, 15, true),
            new Animal("Dori", Animal.Type.FISH, Animal.Sex.F, 3, 5, 7, false),
            new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, 5, 10, 5, true)
        );
    }

    @Test
    @DisplayName("Task3 Test")
    void testTask3() {
        // act
        final var resultMap = AllTasks.task3(ANIMALS);

        // assert
        assertThat(resultMap).containsAllEntriesOf(Map.ofEntries(
            Map.entry(Animal.Type.SPIDER, 1),
            Map.entry(Animal.Type.DOG, 3),
            Map.entry(Animal.Type.CAT, 1),
            Map.entry(Animal.Type.FISH, 1),
            Map.entry(Animal.Type.BIRD, 1)
        ));
    }

    @Test
    @DisplayName("Task4 Test Valid")
    void testTask4Valid() {
        // act
        final var resultAnimal = AllTasks.task4(ANIMALS);

        // assert
        assertThat(resultAnimal).isEqualTo(
            new Animal("Strelka", Animal.Type.DOG, Animal.Sex.F, 4, 21, 22, true)
        );
    }

    @Test
    @DisplayName("Task4 Test Exception")
    void testTask4NotValid() {
        // arrange
        final List<Animal> animals = List.of();

        // assert
        assertThrows(NullPointerException.class, () -> AllTasks.task4(animals));
    }

    @Test
    @DisplayName("Task5 Test")
    void testTask5() {
        // act
        final var sex = AllTasks.task5(ANIMALS);

        // assert
        assertThat(sex).isEqualTo(Animal.Sex.M);
    }
}

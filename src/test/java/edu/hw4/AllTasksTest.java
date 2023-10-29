package edu.hw4;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.util.Map;

public class AllTasksTest {

    private static final Animal ALEX = new Animal("Alex", Animal.Type.CAT, Animal.Sex.M, 13, 30, 15, true);
    private static final Animal KESHA = new Animal("Kesha", Animal.Type.BIRD, Animal.Sex.M, 5, 10, 5, true);
    private static final Animal DORI = new Animal("Dori", Animal.Type.FISH, Animal.Sex.F, 3, 5, 7, false);
    private static final Animal SHNYUK =
        new Animal("Spi der Shnyuk", Animal.Type.SPIDER, Animal.Sex.M, 15, 110, 30, true);
    private static final Animal BARS = new Animal("Bars", Animal.Type.DOG, Animal.Sex.M, 6, 20, 17, false);
    private static final Animal STRELKA = new Animal("Strelka", Animal.Type.DOG, Animal.Sex.F, 4, 21, 22, true);
    private static final Animal BELKA = new Animal("Belka", Animal.Type.DOG, Animal.Sex.F, 2, 19, 21, true);
    private static final List<Animal> ANIMALS = List.of(
        ALEX,
        KESHA,
        DORI,
        SHNYUK,
        BARS,
        STRELKA,
        BELKA
    );

    @Test
    @DisplayName("Task1 Test")
    void testTask1() {
        // act
        final var sortedAnimals = AllTasks.task1(ANIMALS);

        // assert
        assertThat(sortedAnimals).containsExactly(
            DORI,
            KESHA,
            BELKA,
            BARS,
            STRELKA,
            ALEX,
            SHNYUK
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
            SHNYUK,
            STRELKA,
            BELKA,
            BARS,
            ALEX
        );

        assertThat(sortedAnimals2).containsExactly(
            SHNYUK,
            STRELKA,
            BELKA,
            BARS,
            ALEX,
            DORI,
            KESHA
        );
    }

    @Test
    @DisplayName("Task3 Test")
    void testTask3() {
        // act
        final var kindsOfAnimals = AllTasks.task3(ANIMALS);

        // assert
        assertThat(kindsOfAnimals).containsAllEntriesOf(Map.ofEntries(
            Map.entry(Animal.Type.SPIDER, 1L),
            Map.entry(Animal.Type.DOG, 3L),
            Map.entry(Animal.Type.CAT, 1L),
            Map.entry(Animal.Type.FISH, 1L),
            Map.entry(Animal.Type.BIRD, 1L)
        ));
    }

    @Test
    @DisplayName("Task4 Test Valid")
    void testTask4Valid() {
        // act
        final var resultAnimal = AllTasks.task4(ANIMALS);

        // assert
        assertThat(resultAnimal).isEqualTo(SHNYUK);
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

    @Test
    @DisplayName("Task6 Test")
    void testTask6() {
        // act
        final var theMostHeavyAnimals = AllTasks.task6(ANIMALS);

        // assert
        assertThat(theMostHeavyAnimals).containsAllEntriesOf(Map.ofEntries(
                Map.entry(
                    Animal.Type.SPIDER,
                    SHNYUK
                ),
                Map.entry(
                    Animal.Type.DOG,
                    STRELKA
                ),
                Map.entry(
                    Animal.Type.CAT,
                    ALEX
                ),
                Map.entry(
                    Animal.Type.BIRD,
                    KESHA
                ),
                Map.entry(
                    Animal.Type.FISH,
                    DORI
                )
            )
        );
    }

    @Test
    @DisplayName("Task7 Test")
    void testTask7() {
        // arrange
        int k1 = 2;
        int k2 = 10;

        // act
        final var theSecondOldest = AllTasks.task7(ANIMALS, k1);
        final var theYoungest = AllTasks.task7(ANIMALS, k2);

        // assert
        assertThat(theSecondOldest).isEqualTo(ALEX);
        assertThat(theYoungest).isEqualTo(BELKA);
    }

    @Test
    @DisplayName("Task8 Test")
    void testTask8() {
        // arrange
        int k1 = 20;
        int k2 = 200;
        int k3 = 2;

        // act
        final var resultAnimal1 = AllTasks.task8(ANIMALS, k1);
        final var resultAnimal2 = AllTasks.task8(ANIMALS, k2);
        final var resultAnimal3 = AllTasks.task8(ANIMALS, k3);

        // assert
        assertTrue(resultAnimal1.isPresent());
        assertThat(resultAnimal1.get()).isEqualTo(BELKA);
        assertTrue(resultAnimal2.isPresent());
        assertThat(resultAnimal2.get()).isEqualTo(SHNYUK);
        assertFalse(resultAnimal3.isPresent());
    }

    @Test
    @DisplayName("Task9 Test")
    void testTask9() {
        // act
        final var pawsCount = AllTasks.task9(ANIMALS);

        // assert
        assertThat(pawsCount).isEqualTo(26);
    }

    @Test
    @DisplayName("Task10 Test")
    void testTask10() {
        // act
        final var resultList = AllTasks.task10(ANIMALS);

        // assert
        assertThat(resultList).containsExactlyInAnyOrder(
            ALEX,
            KESHA,
            DORI,
            SHNYUK,
            BARS,
            BELKA
        );
    }

    @Test
    @DisplayName("Task11 Test")
    void testTask11() {
        // act
        final var resultList = AllTasks.task11(ANIMALS);

        // assert
        assertThat(resultList).containsExactlyInAnyOrder(SHNYUK);
    }

    @Test
    @DisplayName("Task12 Test")
    void testTask12() {
        // act
        final var resultAnimalsCount = AllTasks.task12(ANIMALS);

        // assert
        assertThat(resultAnimalsCount).isEqualTo(3);
    }

    @Test
    @DisplayName("Task13 Test")
    void testTask13() {
        // act
        final var resultList = AllTasks.task13(ANIMALS);

        // assert
        assertThat(resultList).containsExactlyInAnyOrder(SHNYUK);
    }

    @Test
    @DisplayName("Task14 Test")
    void testTask14() {
        // arrange
        int k1 = 20;
        int k2 = 100;

        // act
        final var isContainsDog1 = AllTasks.task14(ANIMALS, k1);
        final var isContainsDog2 = AllTasks.task14(ANIMALS, k2);

        // assert
        assertTrue(isContainsDog1);
        assertFalse(isContainsDog2);
    }

    @Test
    @DisplayName("Task15 Test")
    void testTask15() {
        // arrange
        int k1 = 2;
        int l1 = 6;
        int k2 = 0;
        int l2 = 1;

        // act
        final var resultWeight1 = AllTasks.task15(ANIMALS, k1, l1);
        final var resultWeight2 = AllTasks.task15(ANIMALS, k2, l2);

        // assert
        assertThat(resultWeight1).isEqualTo(72);
        assertThat(resultWeight2).isEqualTo(0);
    }

    @Test
    @DisplayName("Task16 Test")
    void testTask16() {
        // act
        final var sortedList = AllTasks.task16(ANIMALS);

        // assert
        assertThat(sortedList).containsExactly(
            ALEX,
            BARS,
            BELKA,
            STRELKA,
            KESHA,
            DORI,
            SHNYUK
        );
    }

    @Test
    @DisplayName("Task17 Test")
    void testTask17() {
        // act
        final var result = AllTasks.task17(ANIMALS);

        // assert
        assertFalse(result);
    }

    @Test
    @DisplayName("Task18 Test")
    void testTask18() {
        // act
        final var theHeaviestFish = AllTasks.task18(List.of(ANIMALS));

        // assert
        assertThat(theHeaviestFish).isEqualTo(DORI);
    }
}

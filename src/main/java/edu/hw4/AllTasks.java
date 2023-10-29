package edu.hw4;

import java.util.Arrays;
import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Function;
import java.util.stream.Collectors;

public class AllTasks {
    public static List<Animal> task1(Collection<Animal> animals) {
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::height))
            .toList();
    }

    public static List<Animal> task2(Collection<Animal> animals, int k) {
        return animals
            .stream()
            .sorted(Comparator.comparingInt(Animal::weight).reversed())
            .limit(k)
            .toList();
    }

    public static Map<Animal.Type, Long> task3(Collection<Animal> animals) {
        return animals
            .stream()
            .collect(Collectors.groupingBy(Animal::type, Collectors.counting()));
    }

    public static Animal task4(Collection<Animal> animals) {
        final var animal = animals
            .stream()
            .max(Comparator.comparingInt(animal1 -> animal1.name().length()));
        if (animal.isPresent()) {
            return animal.get();
        } else {
            throw new NullPointerException();
        }
    }

    public static Animal.Sex task5(Collection<Animal> animals) { // TODO
        return
            animals
                .stream()
                .filter(animal -> animal.sex() == Animal.Sex.F)
                .count()
                >
                animals
                    .stream()
                    .filter(animal -> animal.sex() == Animal.Sex.M)
                    .count()
                ? Animal.Sex.F : Animal.Sex.M;
    }

    public static Map<Animal.Type, Animal> task6(Collection<Animal> animals) {
        return
            animals
                .stream()
                .collect(Collectors.toMap(
                    Animal::type,
                    Function.identity(),
                    BinaryOperator.maxBy(Comparator.comparingInt(Animal::weight))
                ));
    }

    public static Animal task7(Collection<Animal> animals, int k) {
        return
            animals
                .stream()
                .sorted(Comparator.comparingInt(Animal::age).reversed())
                .limit(k)
                .toList()
                .getLast();
    }

    public static Optional<Animal> task8(Collection<Animal> animals, int k) {
        return
            animals
                .stream()
                .filter(animal -> animal.height() < k)
                .max(Comparator.comparingInt(Animal::weight));
    }

    public static Integer task9(Collection<Animal> animals) {
        return
            animals
                .stream()
                .mapToInt(Animal::paws)
                .sum();
    }

    public static List<Animal> task10(Collection<Animal> animals) {
        return
            animals
                .stream()
                .filter(animal -> animal.paws() != animal.age())
                .toList();
    }

    @SuppressWarnings("MagicNumber")
    public static List<Animal> task11(Collection<Animal> animals) {
        return
            animals
                .stream()
                .filter(animal -> animal.bites() && animal.height() > 100)
                .toList();
    }

    public static Integer task12(Collection<Animal> animals) {
        return
            Math.toIntExact(animals
                .stream()
                .filter(animal -> animal.weight() > animal.height())
                .count());
    }

    public static List<Animal> task13(Collection<Animal> animals) {
        return
            animals
                .stream()
                .filter(animal -> animal.name().split(" ").length > 2)
                .toList();
    }

    public static boolean task14(Collection<Animal> animals, int k) {
        return
            animals
                .stream()
                .anyMatch(animal -> animal.type().equals(Animal.Type.DOG) && animal.height() > k);
    }

    public static Integer task15(Collection<Animal> animals, int k, int l) {
        return
            animals
                .stream()
                .mapToInt(animal -> (animal.age() >= k && animal.age() <= l) ? animal.weight() : 0)
                .sum();
    }

    public static List<Animal> task16(Collection<Animal> animals) {
        return
            animals
                .stream()
                .sorted(Comparator.comparing(Animal::type)
                    .thenComparing(Animal::sex)
                    .thenComparing(Animal::name))
                .toList();
    }

    public static boolean task17(Collection<Animal> animals) { // TODO?
        return
            animals
                .stream()
                .filter(animal -> animal.type().equals(Animal.Type.SPIDER) && animal.bites())
                .count()
                >
                animals
                    .stream()
                    .filter(animal -> animal.type().equals(Animal.Type.DOG) && animal.bites())
                    .count();
    }

    public static Animal task18(Collection<Collection<Animal>> animalsLists) {
        return
            animalsLists
                .stream()
                .flatMap(Collection::stream)
                .filter(animal -> animal.type().equals(Animal.Type.FISH))
                .max(Comparator.comparingInt(Animal::weight))
                .orElse(null);
    }

    private AllTasks() {
    }
}

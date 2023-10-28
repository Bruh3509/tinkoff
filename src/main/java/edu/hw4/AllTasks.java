package edu.hw4;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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

    public static Map<Animal.Type, Integer> task3(Collection<Animal> animals) {
        Map<Animal.Type, Integer> resultMap = new HashMap<>();
        animals
            .forEach(animal -> {
                    if (resultMap.containsKey(animal.type())) {
                        resultMap.put(animal.type(), resultMap.get(animal.type()) + 1);
                    } else {
                        resultMap.put(animal.type(), 1);
                    }
                }
            );

        return resultMap;
    }

    public static Animal task4(Collection<Animal> animals) {
        final var animal = animals
            .stream()
            .max(Comparator.comparing(Animal::name));
        if (animal.isPresent()) {
            return animal.get();
        } else {
            throw new NullPointerException();
        }
    }

    public static Animal.Sex task5(Collection<Animal> animals) {
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

    private AllTasks() {
    }
}

package edu.hw4;

import java.util.HashSet;
import java.util.Set;

public record ValidationError(String errorField) {
    private static final int MAX_HEIGHT = 150;
    private static final int MAX_AGE = 50;

    public static Set<ValidationError> validateErrors(Animal animal) {
        Set<ValidationError> resultErrorsSet = new HashSet<>();
        if (animal.weight() < 0) {
            resultErrorsSet.add(new ValidationError("Weight"));
        }
        if (animal.height() < 0 || animal.height() > MAX_HEIGHT) {
            resultErrorsSet.add(new ValidationError("Height"));
        }
        if (animal.age() < 0 || animal.age() > MAX_AGE) {
            resultErrorsSet.add(new ValidationError("Age"));
        }

        return resultErrorsSet;
    }
}

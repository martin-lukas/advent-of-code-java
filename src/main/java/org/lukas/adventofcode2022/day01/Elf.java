package org.lukas.adventofcode2022.day01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

record Elf(List<Integer> calories) {
    static final Comparator<Elf> BY_TOTAL_CALORIES_ASC =
            Comparator.comparingInt(Elf::totalCalories);

    static Elf from(String calorieString) {
        List<Integer> calories = Arrays
                .stream(calorieString.split("\n"))
                .map(Integer::parseInt)
                .toList();
        return new Elf(calories);
    }

    int totalCalories() {
        return calories.stream().mapToInt(i -> i).sum();
    }
}

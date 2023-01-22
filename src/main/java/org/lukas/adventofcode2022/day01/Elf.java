package org.lukas.adventofcode2022.day01;

import java.util.Arrays;
import java.util.List;

public record Elf(List<Integer> calories) {
    public static Elf from(String calorieString) {
        List<Integer> calories = Arrays
                .stream(calorieString.split("\n"))
                .map(Integer::parseInt)
                .toList();
        return new Elf(calories);
    }

    public int totalCalories() {
        return calories.stream()
                .mapToInt(Integer::intValue)
                .sum();
    }
}

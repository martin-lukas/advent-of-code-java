package org.lukas.adventofcode2022.day1;

import org.lukas.adventofcode2022.utils.FileUtils;
import org.lukas.adventofcode2022.utils.Mappers;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

class CaloriesCounting {
    public static void main(String[] args) {
        var elvenString = FileUtils.inputToString(1);
        List<Integer> elves = Arrays.stream(elvenString.split("\n\n"))
                .map(elfStr -> elfStr.split("\n"))
                .map(Mappers::stringsToInts)
                .map(Mappers::sumIntArr)
                .toList();

        var maxCalories = elves.stream()
                .mapToInt(i -> i)
                .max()
                .orElse(0);
        var sumOfTop3Calories = elves.stream()
                .sorted(Comparator.reverseOrder())
                .limit(3)
                .mapToInt(i -> i)
                .sum();

        System.out.println("Most calories carried by an elf: " + maxCalories);
        System.out.println("Sum of top 3 elves' calories: " + sumOfTop3Calories);
    }
}

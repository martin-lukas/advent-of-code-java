package org.lukas.adventofcode2022.day01;

import org.lukas.adventofcode2022.utils.FileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ElfManager {
    public static void main(String[] args) {
        String elvenString = FileUtils.toString("day01/elf-calories.txt");

        List<Elf> elves = ElfManager.createElves(elvenString);
        int maxCalories = ElfManager.getElfWithMostCalories(elves)
                .map(Elf::totalCalories)
                .orElse(0);

        System.out.println("Most calories carried by an elf: " + maxCalories);

        List<Elf> top3Elves = ElfManager.getTopNElves(elves, 3);
        int sumOfCalories = ElfManager.sumElfCalories(top3Elves);

        System.out.println("Sum of top 3 elves' calories: " + sumOfCalories);
    }

    static List<Elf> createElves(String caloriesString) {
        // each elf separated by empty line
        return Arrays.stream(caloriesString.split("\n\n"))
                .map(Elf::from)
                .toList();
    }

    static Optional<Elf> getElfWithMostCalories(List<Elf> elves) {
        return elves.stream().max(Elf.BY_TOTAL_CALORIES_ASC);
    }

    static List<Elf> getTopNElves(List<Elf> elves, int n) {
        return elves.stream()
                .sorted(Elf.BY_TOTAL_CALORIES_ASC.reversed())
                .limit(n)
                .toList();
    }

    static int sumElfCalories(List<Elf> elves) {
        return elves.stream()
                .map(Elf::totalCalories)
                .mapToInt(i -> i)
                .sum();
    }
}

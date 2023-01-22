package org.lukas.adventofcode2022.day01;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class ElfManager {
    static List<Elf> createElves(String caloriesString) {
        // each elf separated by empty line
        return Arrays.stream(caloriesString.split("\n\n"))
                .map(Elf::from)
                .toList();
    }

    static int getMaxCaloriesCarriedBySingleElf(List<Elf> elves) {
        return elves.stream()
                .map(Elf::totalCalories)
                .mapToInt(Integer::intValue)
                .max()
                .orElse(0);
    }

    static List<Elf> getTopNElves(List<Elf> elves, int n) {
        Comparator<Elf> byHighestTotalCalories = Comparator
                .comparingInt(Elf::totalCalories)
                .reversed();
        Stream<Elf> sortedElves = elves.stream()
                .sorted(byHighestTotalCalories);
        int topBound = Math.min(n, elves.size());

        return sortedElves.toList().subList(0, topBound);
    }

    static int sumElfCalories(List<Elf> elves) {
        return elves.stream()
                .map(Elf::totalCalories)
                .mapToInt(Integer::intValue)
                .sum();
    }
}

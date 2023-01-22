package org.lukas.adventofcode2022.day01;

import org.lukas.adventofcode2022.utils.FileUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

class ElfManager {
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

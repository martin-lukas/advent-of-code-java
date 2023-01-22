package org.lukas.adventofcode2022.day01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

class ElfManager {
    private ElfManager() {
    }

    static List<Elf> loadElves(String calorieSource) throws IOException {
        String[] elfCalories = Files
                .readString(Paths.get(calorieSource))
                .split("\n\n"); // each elf separated by empty line
        return Arrays.stream(elfCalories)
                .map(Elf::from)
                .toList();
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
}

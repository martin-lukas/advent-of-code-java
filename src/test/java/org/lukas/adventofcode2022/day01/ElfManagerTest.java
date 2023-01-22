package org.lukas.adventofcode2022.day01;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class ElfManagerTest {
    @Test
    void testCreatingElves() {
        String elvesString = "10\n20\n\n30\n\n40\n50";
        List<Elf> elves = ElfManager.createElves(elvesString);

        assertEquals(3, elves.size());
    }

    @Test
    void testFindingMaxCaloriesForSingleElf() {
        String elvesString = "10\n20\n\n30\n\n40\n50";
        List<Elf> elves = ElfManager.createElves(elvesString);
        int maxCalories = ElfManager.getElfWithMostCalories(elves)
                .map(Elf::totalCalories)
                .orElse(0);

        assertEquals(90, maxCalories);
    }

    @Test
    void testFindingTop2Elves() {
        String elvesString = "10\n15\n\n30\n\n40\n50";
        List<Elf> elves = ElfManager.createElves(elvesString);
        List<Elf> top2Elves = ElfManager.getTopNElves(elves, 2);

        assertEquals(2, top2Elves.size());

        Set<Integer> totalCaloriesSet = Set.of(
            top2Elves.get(0).totalCalories(),
            top2Elves.get(1).totalCalories()
        );

        assertTrue(totalCaloriesSet.containsAll(Set.of(30, 90)));
    }

    @Test
    void testSummingElvesCalories() {
        String elvesString = "10\n15\n\n30\n\n40\n50";
        List<Elf> elves = ElfManager.createElves(elvesString);

        assertEquals(145, ElfManager.sumElfCalories(elves));
    }
}

package org.lukas.adventofcode2022.day01;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;
import java.util.OptionalInt;

import static org.junit.jupiter.api.Assertions.*;

class ElfManagerTest {
    final String ELF_SRC = "src/test/resources/elf-calories.txt";

    @Test
    void testLoadingElves() throws IOException {
        List<Elf> elves = ElfManager.loadElves(ELF_SRC);
        assertEquals(240, elves.size());
    }

    @Test
    void testFindingMaxCountingCalories() throws IOException {
        List<Elf> elves = ElfManager.loadElves(ELF_SRC);
        OptionalInt maxCalories = elves.stream()
                .map(Elf::totalCalories)
                .mapToInt(Integer::intValue)
                .max();
        assertTrue(maxCalories.isPresent());
        assertEquals(68442, maxCalories.getAsInt());
    }

    @Test
    void testFindingTop3Elves() throws IOException {
        List<Elf> elves = ElfManager.loadElves(ELF_SRC);
        List<Elf> top3Elves = ElfManager.getTopNElves(elves, 3);

        assertEquals(3, top3Elves.size());

        int sum = top3Elves.stream()
                .map(Elf::totalCalories)
                .mapToInt(Integer::intValue)
                .sum();
        assertEquals(204837, sum);
    }
}

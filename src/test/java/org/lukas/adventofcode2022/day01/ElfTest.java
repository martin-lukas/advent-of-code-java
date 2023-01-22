package org.lukas.adventofcode2022.day01;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ElfTest {
    @Test
    void testCreatingElfFromCalorieString() {
        String calorieString = "100\n200\n300";
        Elf elf = Elf.from(calorieString);
        assertNotNull(elf);
        assertNotNull(elf.calories());
        assertEquals(3, elf.calories().size());
    }

    @Test
    void testGettingTotalOfCarriedCalories() {
        String calorieString = "100\n200\n300";
        Elf elf = Elf.from(calorieString);
        assertEquals(600, elf.totalCalories());
    }
}

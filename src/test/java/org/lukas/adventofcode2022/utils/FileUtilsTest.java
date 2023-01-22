package org.lukas.adventofcode2022.utils;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FileUtilsTest {
    @Test
    void testReadingAFileToString() {
        String str = FileUtils.toString(
                "src/test/resources/utils/test-string.txt",
                true
        );
        assertEquals("this is a string\nsecond line", str);
    }

    @Test
    void testReadingAFileWithRelativePath() {
        String str = FileUtils.toString("day01/elf-calories.txt");
        assertFalse(str.isBlank());
    }

    @Test
    void testReadingNonExistentFile() {
        assertThrows(RuntimeException.class, () ->
                FileUtils.toString("src/test/resources/utils/fake-string.txt", true)
        );
    }
}
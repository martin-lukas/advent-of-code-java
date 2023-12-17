package org.lukas.adventofcode.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class FileUtilsTest {

  @Test
  void testReadingAFileToString() {
    String str = FileUtils.toString("src/test/resources/utils/test-string.txt", true);
    assertEquals("this is a string\nsecond line", str);
  }

  @Test
  void testReadingAFileWithRelativePath() {
    String str = FileUtils.toString("year22/day1/input.txt");
    assertFalse(str.isBlank());
  }

  @Test
  void testReadingNonExistentFile() {
    assertThrows(
        RuntimeException.class,
        () -> FileUtils.toString("src/test/resources/utils/fake-string.txt", true)
    );
  }
}
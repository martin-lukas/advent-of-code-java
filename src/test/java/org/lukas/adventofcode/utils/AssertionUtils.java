package org.lukas.adventofcode.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

public class AssertionUtils {
  public static <T> void assertListEquals(List<T> expected, List<T> actual) {
    assertNotNull(expected);
    assertNotNull(actual);
    assertEquals(expected.size(), actual.size());
    for (var i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), actual.get(i));
    }
  }
}

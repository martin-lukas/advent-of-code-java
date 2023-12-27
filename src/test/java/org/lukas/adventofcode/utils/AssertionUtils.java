package org.lukas.adventofcode.utils;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

public class AssertionUtils {
  public static <T> void assertListEquals(List<T> expected, List<T> actual) {
    assertNotNull(expected);
    assertNotNull(actual);
    assertEquals(expected.size(), actual.size());
    for (var i = 0; i < expected.size(); i++) {
      assertEquals(expected.get(i), actual.get(i));
    }
  }

  public static <T> void assertSetEquals(Set<T> expected, Set<T> actual) {
    assertNotNull(expected);
    assertNotNull(actual);
    assertEquals(expected.size(), actual.size());
    for (var el : expected) {
      assertTrue(actual.contains(el));
    }
  }
}

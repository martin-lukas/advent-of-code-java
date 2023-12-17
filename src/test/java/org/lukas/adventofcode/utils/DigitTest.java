package org.lukas.adventofcode.utils;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class DigitTest {

  @Test
  void testConcat() {
    assertEquals(85, Digit.EIGHT.concat(Digit.FIVE));
    assertEquals(5, Digit.ZERO.concat(Digit.FIVE));
  }

  @Test
  void testFrom() {
    assertEquals(Digit.FIVE, Digit.from('5'));
    assertEquals(Digit.FOUR, Digit.from("4".codePointAt(0)));
    assertThrows(IllegalArgumentException.class, () -> Digit.from('o'));
  }
}
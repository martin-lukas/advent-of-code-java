package org.lukas.adventofcode.utils;

import java.util.Arrays;

public enum Digit {
  ONE(1, "one"),
  TWO(2, "two"),
  THREE(3, "three"),
  FOUR(4, "four"),
  FIVE(5, "five"),
  SIX(6, "six"),
  SEVEN(7, "seven"),
  EIGHT(8, "eight"),
  NINE(9, "nine"),
  ZERO(0, "zero");

  public final Integer number;
  public final String spelling;

  Digit(int number, String spelling) {
    this.number = number;
    this.spelling = spelling;
  }

  public int concat(Digit other) {
    return Integer.parseInt(STR. "\{ number }\{ other.number }" );
  }

  public static Digit from(char c) {
    return Arrays.stream(values())
        .filter(n -> n.number == Character.digit(c, 10))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(
            STR. "The character '\{ c }' s not a digit."
        ));
  }

  public static Digit from(int cp) {
    return Arrays.stream(values())
        .filter(n -> n.number == Character.digit(cp, 10))
        .findFirst()
        .orElseThrow(() -> new IllegalArgumentException(
            STR. "The code point '\{ cp }' s not a digit."
        ));
  }

  public char asChar() {
    return Character.forDigit(number, 10);
  }
}

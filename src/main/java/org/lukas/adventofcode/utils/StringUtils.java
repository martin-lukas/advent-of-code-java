package org.lukas.adventofcode.utils;

import java.util.List;
import java.util.stream.IntStream;

public class StringUtils {
  public static List<Integer> indicesOf(String str, String substr) {
    return IntStream
        .iterate(
            str.indexOf(substr),
            index -> index >= 0,
            index -> str.indexOf(substr, index + 1)
        )
        .boxed()
        .toList();
  }

  public static List<Integer> indicesOf(String str, char c) {
    return indicesOf(str, Character.toString(c));
  }
}

package org.lukas.adventofcode.utils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListUtils {

  /**
   * Returns minimum for a non-empty integer list.
   */
  public static Integer min(List<Integer> list) {
    return list.stream().mapToInt(Integer::intValue).min().orElseThrow();
  }

  /**
   * Returns maximum for a non-empty integer list.
   */
  public static Integer max(List<Integer> list) {
    return list.stream().mapToInt(Integer::intValue).max().orElseThrow();
  }

  public static Integer sum(Collection<Integer> list) {
    return list.stream().mapToInt(Integer::intValue).sum();
  }

  public static Integer product(Collection<Integer> list) {
    return list.stream().reduce(1, (acc, cur) -> acc * cur);
  }

  public static Integer toInt(List<Character> chars) {
    return Integer.parseInt(StringUtils.from(chars));
  }

  public static <T> List<T> merge(List<T> list1, List<T> list2) {
    var merged = new ArrayList<>(list1);
    merged.addAll(list2);
    return merged;
  }
}

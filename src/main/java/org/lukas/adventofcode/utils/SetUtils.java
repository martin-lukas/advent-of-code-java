package org.lukas.adventofcode.utils;

import java.util.HashSet;
import java.util.Set;

public class SetUtils {
  public static <T> Set<T> union(Set<T> set1, Set<T> set2) {
    var res = new HashSet<T>();
    for (var el : set1) {
      if (set2.contains(el)) {
        res.add(el);
      }
    }
    return res;
  }
}

package org.lukas.adventofcode.utils;

import java.util.Map;
import java.util.Map.Entry;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class CustomCollectors {

  public static <K, V> Collector<Entry<K, V>, ?, Map<K, V>> toMap() {
    return Collectors.toMap(Entry::getKey, Entry::getValue);
  }
}

package org.lukas.adventofcode.year23;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;
import static org.lukas.adventofcode.utils.CustomCollectors.toMap;
import static org.lukas.adventofcode.utils.Logger.log;

import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import org.lukas.adventofcode.utils.Digit;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.utils.ListUtils;
import org.lukas.adventofcode.utils.StringUtils;

/**
 * <a href="https://adventofcode.com/2023/day/1">Day 1: Trebuchet?!</a>
 */
class Day1 {

  public static void main(String[] args) {
    var lines = FileUtils.inputToLines(23, 1);
    var sumOfCalibrationValues = lines.stream()
        .map(Day1::getDigitOccurences)
        .map(Day1::getCalibrationValue)
        .mapToInt(Integer::intValue)
        .sum();
    var sumOfCalibrationValuesWithSpelledOut = lines.stream()
        .map(line -> merge(getDigitOccurences(line), getDigitSpelledOutOccurences(line)))
        .map(Day1::getCalibrationValue)
        .mapToInt(Integer::intValue)
        .sum();
    log.info(STR. "Sum of values: \{ sumOfCalibrationValues }" );
    log.info(STR. "Sum of values (inc. spelled out): \{ sumOfCalibrationValuesWithSpelledOut }" );
  }

  private static Map<Digit, List<Integer>> getDigitOccurences(String line) {
    return getDigitOccurences(digit -> StringUtils.indicesOf(line, digit.asChar()));
  }

  private static Map<Digit, List<Integer>> getDigitSpelledOutOccurences(String line) {
    return getDigitOccurences(digit -> StringUtils.indicesOf(line, digit.spelling));
  }

  private static Map<Digit, List<Integer>> getDigitOccurences(
      Function<Digit, List<Integer>> extractOccurrencesFn
  ) {
    return Arrays.stream(Digit.values())
        .collect(toMap(
            identity(),
            extractOccurrencesFn,
            ListUtils::merge
        ))
        .entrySet().stream()
        .filter(e -> !e.getValue().isEmpty())
        .collect(toMap());
  }

  private static int getCalibrationValue(Map<Digit, List<Integer>> occurrences) {
    if (occurrences.isEmpty()) {
      throw new IllegalArgumentException("There were no digit occurrences in this line.");
    }
    var first = occurrences.entrySet().stream()
        .min(Comparator.comparing(e -> ListUtils.min(e.getValue())))
        .orElseThrow();
    var last = occurrences.entrySet().stream()
        .max(Comparator.comparing(e -> ListUtils.max(e.getValue())))
        .orElseThrow();
    return first.getKey().concat(last.getKey());
  }

  private static Map<Digit, List<Integer>> merge(
      Map<Digit, List<Integer>> map1,
      Map<Digit, List<Integer>> map2
  ) {
    var mergedMap = new HashMap<>(map1);
    map2.forEach((key, value) -> mergedMap.merge(key, value, ListUtils::merge));
    return mergedMap;
  }
}

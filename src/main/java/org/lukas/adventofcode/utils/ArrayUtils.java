package org.lukas.adventofcode.utils;

import java.util.Arrays;
import java.util.List;

public class ArrayUtils {
    public static List<Integer> stringsToInts(String[] strings) {
        return Arrays.stream(strings)
                .map(Integer::parseInt)
                .toList();
    }

    public static List<Long> stringsToLongs(String[] strings) {
        return Arrays.stream(strings)
            .map(Long::parseLong)
            .toList();
    }
}

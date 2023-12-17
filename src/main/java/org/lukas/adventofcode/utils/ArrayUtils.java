package org.lukas.adventofcode.utils;

import java.util.Arrays;

public class ArrayUtils {
    public static int[] stringsToInts(String[] strings) {
        return Arrays.stream(strings)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int sumIntArr(int[] array) {
        return Arrays.stream(array).sum();
    }
}

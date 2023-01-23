package org.lukas.adventofcode2022.utils;

import java.util.Arrays;

public class Mappers {
    public static int[] stringsToInts(String[] strings) {
        return Arrays.stream(strings)
                .mapToInt(Integer::parseInt)
                .toArray();
    }

    public static int sumIntArr(int[] array) {
        return Arrays.stream(array).sum();
    }
}

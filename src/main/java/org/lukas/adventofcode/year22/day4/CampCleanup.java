package org.lukas.adventofcode.year22.day4;

import org.lukas.adventofcode.utils.FileUtils;

import java.util.List;
import java.util.Optional;

public class CampCleanup {
    public static void main(String[] args) {
        List<int[][]> pairs = FileUtils.inputToLines(22, 4).stream()
                .map(pair -> pair.split(","))
                .filter(pair -> pair.length == 2)
                .map(CampCleanup::createRanges)
                .flatMap(Optional::stream)
                .toList();

        int enclosedRanges = 0;
        for (int[][] pair : pairs) {
            if (isRangeWithinRange(pair[0], pair[1])
                    || isRangeWithinRange(pair[1], pair[0])
            ) {
                enclosedRanges++;
            }
        }

        int overlappingRanges = 0;
        for (int[][] pair : pairs) {
            if (isRangeOverlap(pair[0], pair[1])) {
                overlappingRanges++;
            }
        }

        System.out.println("Number of section ranges contained in the other: " + enclosedRanges);
        System.out.println("Number of overlapping section ranges: " + overlappingRanges);
    }

    static Optional<int[][]> createRanges(String[] strRanges) {
        String[] rangeA = strRanges[0].split("-");
        String[] rangeB = strRanges[1].split("-");

        if (rangeA.length == 2 && rangeB.length == 2) {
            return Optional.of(new int[][] {
                    {Integer.parseInt(rangeA[0]), Integer.parseInt(rangeA[1])},
                    {Integer.parseInt(rangeB[0]), Integer.parseInt(rangeB[1])}
            });
        } else {
            return Optional.empty();
        }
    }

    static boolean isRangeWithinRange(int[] widerRange, int[] narrowerRange) {
        return widerRange[0] <= narrowerRange[0] && widerRange[1] >= narrowerRange[1];
    }

    static boolean isRangeOverlap(int[] rangeA, int[] rangeB) {
        return inRange(rangeB, rangeA[0]) || inRange(rangeB, rangeA[1])
                || inRange(rangeA, rangeB[0]) || inRange(rangeA, rangeB[1]);
    }

    static boolean inRange(int[] range, int tested) {
        return tested >= range[0] && tested <= range[1];
    }
}

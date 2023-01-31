package org.lukas.adventofcode.year22.day3;

import org.lukas.adventofcode.utils.FileUtils;

import java.util.Optional;

public class RucksackReorganization {
    private static final String SCORES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        int scoresSum = FileUtils.inputToLines(22, 3)
                .stream()
                .map(RucksackReorganization::splitInHalf)
                .filter(sections -> sections.length == 2)
                .map(sections -> findCommonItem(sections[0], sections[1]))
                .flatMap(Optional::stream)
                .map(item -> SCORES.indexOf(item) + 1)
                .mapToInt(i -> i)
                .sum();
        System.out.println("Sum of scores: " + scoresSum);
    }

    static char[][] splitInHalf(String str) {
        var mid = str.length() / 2;
        return new char[][]{
                str.substring(0, mid).toCharArray(),
                str.substring(mid).toCharArray()
        };
    }

    static Optional<Character> findCommonItem(char[] items1, char[] items2) {
        for (char left : items1) {
            for (char right : items2) {
                if (left == right) {
                    return Optional.of(left);
                }
            }
        }
        return Optional.empty();
    }
}

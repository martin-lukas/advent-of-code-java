package org.lukas.adventofcode.year22.day3;

import org.lukas.adventofcode.utils.FileUtils;

import java.util.Arrays;
import java.util.Optional;

public class RucksackReorganization {
    private static final String SCORES = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";

    public static void main(String[] args) {
        var rucksacks = FileUtils.inputToLines(22, 3);

        int sectionCommonScoreSum = rucksacks.stream()
                .map(RucksackReorganization::splitInHalf)
                .filter(sections -> sections.length == 2)
                .map(sections -> findCommonItem(sections[0], sections[1]))
                .flatMap(Optional::stream)
                .map(RucksackReorganization::score)
                .mapToInt(i -> i)
                .sum();

        int groupCommonScoreSum = 0;
        for (int i = 0; i < rucksacks.size(); i += 3) {
            char[][] group = rucksacks.subList(i, i + 3).stream()
                    .map(String::toCharArray)
                    .toArray(char[][]::new);
            groupCommonScoreSum += findCommonItem(group[0], group[1], group[2])
                    .map(RucksackReorganization::score)
                    .orElse(0);
        }

        System.out.println("Sum of section-common item scores: " + sectionCommonScoreSum);
        System.out.println("Sum of group-common item scores: " + groupCommonScoreSum);
    }

    static char[][] splitInHalf(String str) {
        var mid = str.length() / 2;
        return new char[][]{
                str.substring(0, mid).toCharArray(),
                str.substring(mid).toCharArray()
        };
    }

    static Optional<Character> findCommonItem(char[] items1, char[] ...itemsRest) {
        for (char tested : items1) {
            boolean isShared = Arrays.stream(itemsRest)
                    .allMatch(items -> containsItem(items, tested));
            if (isShared) {
                return Optional.of(tested);
            }
        }
        return Optional.empty();
    }

    static boolean containsItem(char[] items, char item) {
        for (char tested : items) {
            if (tested == item) {
                return true;
            }
        }
        return false;
    }

    static int score(char item) {
        return SCORES.indexOf(item) + 1;
    }
}

package org.lukas.adventofcode.year22.day2;

import org.lukas.adventofcode.utils.FileUtils;

import java.util.List;
import java.util.Map;

class RockPaperScissors {
    private static final Map<String, Integer> NAIVE_CHOICE = Map.of(
            "X", 1, "Y", 2, "Z", 3
    );

    private static final Map<String, Integer> NAIVE_ALG = Map.of(
            "A X", 3, "A Y", 6, "A Z", 0,
            "B X", 0, "B Y", 3, "B Z", 6,
            "C X", 6, "C Y", 0, "C Z", 3
    );

    private static final Map<String, Integer> INFORMED_CHOICE = Map.of(
            "X", 0, "Y", 3, "Z", 6
    );

    private static final Map<String, Integer> INFORMED_ALG = Map.of(
            "A X", 3, "A Y", 1, "A Z", 2,
            "B X", 1, "B Y", 2, "B Z", 3,
            "C X", 2, "C Y", 3, "C Z", 1
    );

    public static void main(String[] args) {
        List<String> lines = FileUtils.inputToLines(22, 2);
        int naiveScore = lines.stream()
                .map(pair -> NAIVE_CHOICE.get(pair.substring(pair.length() - 1))
                        + NAIVE_ALG.get(pair))
                .mapToInt(i -> i)
                .sum();
        int informedScore = lines.stream()
                .map(pair -> INFORMED_CHOICE.get(pair.substring(pair.length() - 1))
                        + INFORMED_ALG.get(pair))
                .mapToInt(i -> i)
                .sum();

        System.out.println("Naive choice score: " + naiveScore);
        System.out.println("Informed choice score: " + informedScore);
    }
}

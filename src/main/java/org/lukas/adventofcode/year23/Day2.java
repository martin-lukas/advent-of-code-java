package org.lukas.adventofcode.year23;

import static org.lukas.adventofcode.utils.Logger.log;
import static org.lukas.adventofcode.year23.Day2.Color.BLUE;
import static org.lukas.adventofcode.year23.Day2.Color.GREEN;
import static org.lukas.adventofcode.year23.Day2.Color.RED;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.utils.ListUtils;

/**
 * <a href="https://adventofcode.com/2023/day/2">Day 2: Cube Conundrum</a>
 */
class Day2 {

  public static void main(String[] args) {
    var lines = FileUtils.inputToLines(23, 2);
    var games = lines.stream().map(Game::parse).toList();
    var sumOfValidGameIds = games.stream()
        .filter(g -> g.isValidWrt(Map.of(RED, 12, GREEN, 13, BLUE, 14)))
        .mapToInt(Game::id)
        .sum();
    var sumOfPowers = games.stream()
        .map(Game::requiredCubeCounts)
        .mapToInt(ListUtils::product)
        .sum();
    log.info("Sum of valid game IDs: " + sumOfValidGameIds);
    log.info("Sum of powers: " + sumOfPowers);
  }

  record Game(int id, List<Map<Color, Integer>> rounds) {

    static Game parse(String gameStr) {
      var gameAndRounds = gameStr.split(": ");
      if (gameAndRounds.length != 2) {
        throw new IllegalArgumentException(
            STR. "The game string '\{ gameStr }' doesn't have valid game ID and rounds parts."
        );
      }
      return new Game(parseId(gameAndRounds[0]), parseRounds(gameAndRounds[1]));
    }

    private static Integer parseId(String gameIdStr) {
      var labelAndId = gameIdStr.split(" ");
      if (labelAndId.length != 2) {
        throw new IllegalArgumentException(
            STR. "The game ID string '\{ gameIdStr }' isn't valid."
        );
      }
      return Integer.parseInt(labelAndId[1]);
    }

    private static List<Map<Color, Integer>> parseRounds(String roundsStr) {
      return Arrays.stream(roundsStr.split("; ")).map(Game::parseRound).toList();
    }

    private static Map<Color, Integer> parseRound(String roundStr) {
      var round = new HashMap<Color, Integer>();
      for (var roundPart : roundStr.split(", ")) {
        var countAndColor = roundPart.split(" ");
        var count = Integer.parseInt(countAndColor[0]);
        var color = Color.from(countAndColor[1]);
        round.put(color, count);
      }
      return round;
    }

    static boolean isRoundValidWrt(Map<Color, Integer> cubes, Map<Color, Integer> round) {
      return Arrays.stream(Color.values())
          .allMatch(c -> round.getOrDefault(c, 0) <= cubes.get(c));
    }

    boolean isValidWrt(Map<Color, Integer> cubes) {
      return rounds.stream().allMatch(r -> isRoundValidWrt(cubes, r));
    }

    List<Integer> requiredCubeCounts() {
      return List.of(
          requiredCubeCountForColor(RED),
          requiredCubeCountForColor(GREEN),
          requiredCubeCountForColor(BLUE)
      );
    }

    private Integer requiredCubeCountForColor(Color color) {
      return rounds.stream()
          .map(m -> m.get(color))
          .filter(Objects::nonNull)
          .mapToInt(Integer::intValue)
          .max()
          .orElseThrow();
    }
  }

  enum Color {
    RED("red"),
    GREEN("green"),
    BLUE("blue");

    private final String value;

    Color(String value) {
      this.value = value;
    }

    static Color from(String value) {
      return Arrays.stream(values())
          .filter(c -> c.value.equals(value))
          .findFirst()
          .orElseThrow();
    }
  }
}

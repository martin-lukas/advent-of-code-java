package org.lukas.adventofcode.year23;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;
import org.junit.jupiter.api.Test;
import org.lukas.adventofcode.year23.Day2.Color;
import org.lukas.adventofcode.year23.Day2.Game;

class Day2Test {

  @Test
  void testGameValid() {
    var cubes = Map.of(Color.RED, 1, Color.GREEN, 2, Color.BLUE, 3);
    var validRound = Map.of(Color.RED, 1, Color.GREEN, 1);
    var invalidRound = Map.of(Color.RED, 2, Color.BLUE, 3);
    var validGame = new Game(1, List.of(validRound));
    var invalidGame = new Game(2, List.of(validRound, invalidRound));
    assertTrue(validGame.isValidWrt(cubes));
    assertFalse(invalidGame.isValidWrt(cubes));
  }

  @Test
  void testRoundValid() {
    var cubes = Map.of(Color.RED, 1, Color.GREEN, 2, Color.BLUE, 3);
    var validRound = Map.of(Color.RED, 1, Color.GREEN, 1);
    assertTrue(Game.isRoundValidWrt(cubes, validRound));
    var invalidRound = Map.of(Color.RED, 2, Color.BLUE, 3);
    assertFalse(Game.isRoundValidWrt(cubes, invalidRound));
  }
}
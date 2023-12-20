package org.lukas.adventofcode.year23;

import static org.lukas.adventofcode.utils.AssertionUtils.assertListEquals;
import static org.lukas.adventofcode.utils.Logger.log;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.utils.ListUtils;

class Day3Test {

  @Test
  void testParseSchematic() {
    var schematic = """
        4.11.
        .*...
        ..35.""";
    var schematicArray = new char[][]{
        {'4', '.', '1', '1', '.'},
        {'.', '*', '.', '.', '.'},
        {'.', '.', '3', '5', '.'},
    };
    Assertions.assertArrayEquals(schematicArray, Day3.parseSchematic(schematic));
  }

  @Test
  void testGetParts() {
    var schematic = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617....356""";
    assertListEquals(List.of(467, 35, 633, 356), Day3.getParts(Day3.parseSchematic(schematic)));
  }

  @Test
  void testPart1() {
    Assertions.assertEquals(535235, Day3.sumOfParts(FileUtils.inputToString(23, 3)));
  }

}
package org.lukas.adventofcode.year23;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.utils.ListUtils;
import org.lukas.adventofcode.year23.Day3.Part;

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
    var parts = Day3.getParts(Day3.parseSchematic(schematic));
    assertEquals(List.of(467, 35, 633, 356), parts.stream().map(Part::value).toList());
  }

  @Test
  void testGetGearRatios() {
    var schematicStr = """
        467..114..
        ...*......
        ..35..633.
        ......#...
        617*......
        .....+.58.
        ..592.....
        ......755.
        ...$.*....
        .664.598..""";
    var schematic = Day3.parseSchematic(schematicStr);
    var parts = Day3.getParts(schematic);
    var ratios = Day3.getGearRatios(schematic, parts);
    assertFalse(ratios.isEmpty());
    assertEquals(467835, ListUtils.sum(ratios));
  }

  @Test
  void testPart1() {
    var parts = Day3.getParts(Day3.parseSchematic(FileUtils.inputToString(23, 3)));
    assertEquals(535235, Day3.sumOfParts(parts));
  }

  @Test
  void testPart2() {
    var schematic = Day3.parseSchematic(FileUtils.inputToString(23, 3));
    var parts = Day3.getParts(schematic);
    assertEquals(79844424, Day3.sumOfGearRatios(schematic, parts));
  }

}
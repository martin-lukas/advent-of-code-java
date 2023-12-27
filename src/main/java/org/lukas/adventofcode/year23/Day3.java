package org.lukas.adventofcode.year23;

import static org.lukas.adventofcode.utils.Logger.log;

import java.util.ArrayList;
import java.util.List;
import java.util.OptionalInt;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.utils.ListUtils;

/**
 * <a href="https://adventofcode.com/2023/day/3">Day 3: Gear Ratios</a>
 */
class Day3 {

  public static void main(String[] args) {
    var schematicStr = FileUtils.inputToString(23, 3);
    var schematic = parseSchematic(schematicStr);
    var parts = getParts(schematic);
    log.info(STR."Sum of engine parts: \{sumOfParts(parts)}");
    log.info(STR."Sum of gear ratios: \{sumOfGearRatios(schematic, parts)}");
  }

  static int sumOfParts(List<Part> parts) {
    return ListUtils.sum(parts.stream().map(Part::value).toList());
  }

  static int sumOfGearRatios(char[][] schematic, List<Part> parts) {
    return ListUtils.sum(getGearRatios(schematic, parts));
  }

  static List<Part> getParts(char[][] schematic) {
    var parts = new ArrayList<Part>();
    for (var row = 0; row < schematic.length; row++) {
      var digits = new ArrayList<Character>();
      for (var col = 0; col < schematic[row].length; col++) {
        var currentChar = schematic[row][col];
        var isDigit = Character.isDigit(currentChar);
        var isLastChar = col == (schematic[row].length - 1);
        var isNextNonDigit = !isLastChar && !Character.isDigit(schematic[row][col + 1]);

        if (isDigit) {
          digits.add(currentChar);
        }

        if (isDigit && (isLastChar || isNextNonDigit)) {
          var numStart = col + 1 - digits.size();
          var isValidPart = isValidPart(schematic, row, numStart, col);
          if (isValidPart) {
            parts.add(new Part(ListUtils.toInt(digits), new CoordinateRange(row, numStart, col)));
          }
          digits.clear();
        }
      }
    }
    return parts;
  }

  static List<Integer> getGearRatios(char[][] schematic, List<Part> parts) {
    var ratios = new ArrayList<Integer>();
    for (var row = 0; row < schematic.length; row++) {
      for (var col = 0; col < schematic[row].length; col++) {
        getGearRatio(schematic, parts, row, col).ifPresent(ratios::add);
      }
    }
    return ratios;
  }

  static OptionalInt getGearRatio(char[][] schematic, List<Part> parts, int row, int col) {
    if (!isGearIndicator(schematic[row][col])) {
      return OptionalInt.empty();
    }
    var gearParts = new ArrayList<Integer>();
    for (
        var checkedRow = Math.max(row - 1, 0);
        checkedRow <= Math.min(schematic.length - 1, row + 1);
        checkedRow++
    ) {
      for (
          var checkedCol = Math.max(col - 1, 0);
          checkedCol <= Math.min(schematic[row].length - 1, col + 1);
          checkedCol++
      ) {
        for (var part : parts) {
          var partRange = part.range();
          if (checkedRow == partRange.row()
              && checkedCol >= partRange.colStart()
              && checkedCol <= partRange.colEnd()
          ) {
            gearParts.add(part.value());
            // Skip to end of found number, to avoid checking it multiple times.
            checkedCol = partRange.colEnd();
          }
        }
      }
    }
    if (gearParts.size() != 2) {
      return OptionalInt.empty();
    } else {
      return OptionalInt.of(ListUtils.product(gearParts));
    }
  }

  static boolean isValidPart(char[][] schematic, int row, int numStart, int numEnd) {
    for (
        var checkedRow = Math.max(row - 1, 0);
        checkedRow <= Math.min(schematic.length - 1, row + 1);
        checkedRow++
    ) {
      for (
          var checkedCol = Math.max(numStart - 1, 0);
          checkedCol <= Math.min(schematic[row].length - 1, numEnd + 1);
          checkedCol++
      ) {
        var isNumArea = checkedRow == row && checkedCol >= numStart && checkedCol <= numEnd;
        if (!(isNumArea)) {
          var testedChar = schematic[checkedRow][checkedCol];
          if (isPartIndicator(testedChar)) {
            return true;
          }
        }
      }
    }
    return false;
  }

  private static boolean isPartIndicator(char c) {
    return c != '.' && !Character.isDigit(c);
  }

  private static boolean isGearIndicator(char c) {
    return c == '*';
  }

  static char[][] parseSchematic(String schematicStr) {
    var lines = schematicStr.split("\n");
    var schematic = new char[lines.length][lines[0].length()];
    for (var i = 0; i < lines.length; i++) {
      schematic[i] = lines[i].toCharArray();
    }
    return schematic;
  }

  record Part(int value, CoordinateRange range) {}

  record CoordinateRange(int row, int colStart, int colEnd) {}
}

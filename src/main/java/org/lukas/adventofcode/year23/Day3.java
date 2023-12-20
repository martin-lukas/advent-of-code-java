package org.lukas.adventofcode.year23;

import static org.lukas.adventofcode.utils.Logger.log;

import java.util.ArrayList;
import java.util.List;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.utils.ListUtils;

/**
 * <a href="https://adventofcode.com/2023/day/3">Day 3: Gear Ratios</a>
 */
class Day3 {

  public static void main(String[] args) {
    var schematicStr = FileUtils.inputToString(23, 3);
    log.info(STR."Sum of engine parts: \{sumOfParts(schematicStr)}");
  }

  static int sumOfParts(String schematicStr) {
    return ListUtils.sum(getParts(parseSchematic(schematicStr)));
  }

  static List<Integer> getParts(char[][] schematic) {
    var parts = new ArrayList<Integer>();
    for (var i = 0; i < schematic.length; i++) {
      var digits = new ArrayList<Character>();
      for (var j = 0; j < schematic[i].length; j++) {
        var currentChar = schematic[i][j];
        var isDigit = Character.isDigit(currentChar);
        var isLastChar = j == schematic[i].length - 1;

        if (isDigit) {
          digits.add(currentChar);
        }

        var validateNumberBeforeEnd = isDigit && isLastChar;
        var validateNumberAtEnd = !isDigit && !digits.isEmpty();

        int numStart = 0, numEnd = 0;
        if (validateNumberBeforeEnd) {
          numStart = j + 1 - digits.size();
          numEnd = j;
        } else if (validateNumberAtEnd) {
          numStart = j - digits.size();
          numEnd = j - 1;
        }

        if (validateNumberBeforeEnd || validateNumberAtEnd) {
          var isValidPart = isValidPart(schematic, i, numStart, numEnd);
          if (isValidPart) {
            parts.add(ListUtils.toInt(digits));
          }
          digits.clear();
        }
      }
    }
    return parts;
  }

  static boolean isValidPart(char[][] schematic, int row, int numStart, int numEnd) {
    for (
        var k = Math.max(row - 1, 0);
        k <= Math.min(schematic.length - 1, row + 1);
        k++
    ) {
      for (
          var l = Math.max(numStart - 1, 0);
          l <= Math.min(schematic[row].length - 1, numEnd + 1);
          l++
      ) {
        var isNumArea = k == row && l >= numStart && l <= numEnd;
        if (!(isNumArea)) {
          var testedChar = schematic[k][l];
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

  static char[][] parseSchematic(String schematicStr) {
    var lines = schematicStr.split("\n");
    var schematic = new char[lines.length][lines[0].length()];
    for (var i = 0; i < lines.length; i++) {
      schematic[i] = lines[i].toCharArray();
    }
    return schematic;
  }
}

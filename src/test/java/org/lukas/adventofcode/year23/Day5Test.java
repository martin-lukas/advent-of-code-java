package org.lukas.adventofcode.year23;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import org.junit.jupiter.api.Test;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.year23.Day5.Aspect;

class Day5Test {

  static final String EXAMPLE = """
      seeds: 79 14 55 13
                                                      
      seed-to-soil map:
      50 98 2
      52 50 48
      
      soil-to-fertilizer map:
      0 15 37
      37 52 2
      39 0 15
      
      fertilizer-to-water map:
      49 53 8
      0 11 42
      42 0 7
      57 7 4
      
      water-to-light map:
      88 18 7
      18 25 70
      
      light-to-temperature map:
      45 77 23
      81 45 19
      68 64 13
      
      temperature-to-humidity map:
      0 69 1
      1 0 69
      
      humidity-to-location map:
      60 56 37
      56 93 4""";

  @Test
  void testParseSourceDestRanges() {
    var rangeSections = Arrays.copyOfRange(EXAMPLE.split("\n\n"), 1, 8);
    var rangeMap = Day5.parseSourceDestRanges(rangeSections);
    assertEquals(Aspect.values().length, rangeMap.keySet().size());
    assertEquals(2, rangeMap.get(Aspect.SEED).size());
    var rangeAndDelta = rangeMap.get(Aspect.SEED).getLast();
    assertEquals(50, rangeAndDelta.range().start());
    assertEquals(97, rangeAndDelta.range().end());
  }

  @Test
  void testGetLocationForSeed() {
    var rangeSections = Arrays.copyOfRange(EXAMPLE.split("\n\n"), 1, 8);
    var rangeMap = Day5.parseSourceDestRanges(rangeSections);
    assertEquals(82, Day5.getLocation(79, rangeMap));
  }

  @Test
  void testMinLocationForSeeds() {
    var sections = EXAMPLE.split("\n\n");
    var seeds = Day5.parseSeeds(sections[0]);
    var rangesMap = Day5.parseSourceDestRanges(Arrays.copyOfRange(sections, 1, 8));
    assertEquals(35L, Day5.minLocationForSeeds(seeds, rangesMap));
  }

  @Test
  void testX() {
    var sections = EXAMPLE.split("\n\n");
    var seedRange = Day5.parseSeedRanges(sections[0]).getLast();
    var rangesMap = Day5.parseSourceDestRanges(Arrays.copyOfRange(sections, 1, 8));
    assertEquals(46L, Day5.minLocationForSeedsInRanges(seedRanges, rangesMap));
  }

  @Test
  void testMinLocationForSeedRanges() {
    var sections = EXAMPLE.split("\n\n");
    var seedRanges = Day5.parseSeedRanges(sections[0]);
    var rangesMap = Day5.parseSourceDestRanges(Arrays.copyOfRange(sections, 1, 8));
    assertEquals(46L, Day5.minLocationForSeedsInRanges(seedRanges, rangesMap));
  }

  @Test
  void testPart1() {
    var input = FileUtils.inputToString(23, 5);
    var sections = input.split("\n\n");
    var seeds = Day5.parseSeeds(sections[0]);
    var rangesMap = Day5.parseSourceDestRanges(Arrays.copyOfRange(sections, 1, 8));
    assertEquals(579439039, Day5.minLocationForSeeds(seeds, rangesMap));
  }

  //@Test
  void testPart2() {
    var input = FileUtils.inputToString(23, 5);
    var sections = input.split("\n\n");
    var seedRanges = Day5.parseSeedRanges(sections[0]);
    var rangesMap = Day5.parseSourceDestRanges(Arrays.copyOfRange(sections, 1, 8));
    assertEquals(0L, Day5.minLocationForSeedsInRanges(seedRanges, rangesMap));
  }
}
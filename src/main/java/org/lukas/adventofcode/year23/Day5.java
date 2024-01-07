package org.lukas.adventofcode.year23;

import static org.lukas.adventofcode.utils.Logger.log;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.lukas.adventofcode.utils.ArrayUtils;
import org.lukas.adventofcode.utils.FileUtils;

/**
 * <a href="https://adventofcode.com/2023/day/5">Day 5: If You Give A Seed A Fertilizer</a>
 */
class Day5 {

  public static void main(String[] args) {
    var input = FileUtils.inputToString(23, 5);
    var sections = input.split("\n\n");
    var seeds = parseSeeds(sections[0]);
    var seedRanges = parseSeedRanges(sections[0]);
    var rangesMap = parseSourceDestRanges(Arrays.copyOfRange(sections, 1, 8));
    log.info(STR."Min location for seeds: \{minLocationForSeeds(seeds, rangesMap)}");
    log.info(
        STR."Min location for seeds in ranges: \{minLocationForSeedsInRanges(seedRanges,
            rangesMap)}"
    );
  }

  static List<Long> parseSeeds(String seedsStr) {
    return ArrayUtils.stringsToLongs(seedsStr.split(": ")[1].split(" "));
  }

  static List<Range> parseSeedRanges(String seedsStr) {
    var seedRangeVals = ArrayUtils.stringsToLongs(seedsStr.split(": ")[1].split(" "));
    if (seedRangeVals.size() % 2 != 0) {
      throw new IllegalArgumentException("The seed ranges are not valid");
    }
    var seedRanges = new ArrayList<Range>();
    for (var i = 0; i < seedRangeVals.size(); i += 2) {
      var rangeStart = seedRangeVals.get(i);
      var rangeWidth = seedRangeVals.get(i + 1);
      seedRanges.add(new Range(rangeStart, rangeStart + rangeWidth));
    }
    return seedRanges;
  }

  static Map<Aspect, List<RangeAndDelta>> parseSourceDestRanges(String[] sections) {
    if (sections.length != Aspect.values().length) {
      throw new IllegalArgumentException("The input doesn't have all sections present");
    }
    var ranges = new HashMap<Aspect, List<RangeAndDelta>>();
    for (String section : sections) {
      var nameRangeStrs = section.split(" map:\n");
      var source = parseSource(nameRangeStrs[0]);
      var sectionRanges = parseRanges(nameRangeStrs[1]);
      ranges.put(source, sectionRanges);
    }
    return ranges;
  }

  static long minLocationForSeeds(List<Long> seeds, Map<Aspect, List<RangeAndDelta>> rangesMap) {
    return seeds.stream()
        .mapToLong(s -> Day5.getLocation(s, rangesMap))
        .min()
        .orElseThrow();
  }

  static long minLocationForSeedsInRanges(
      List<Range> seedRanges,
      Map<Aspect, List<RangeAndDelta>> rangesMap
  ) {
    return seedRanges.stream()
        .mapToLong(r -> minLocationForSeedsInRange(r, rangesMap))
        .min()
        .orElseThrow();
  }

  static long minLocationForSeedsInRange(
      Range seedRange,
      Map<Aspect, List<RangeAndDelta>> rangesMap
  ) {
    var destRangesMap = new HashMap<Aspect, List<Range>>();
    for (var i = 0; i < Aspect.values().length; i++) {
      var source = Aspect.values()[i];
      var ranges = rangesMap.get(source);
      destRangesMap
          .computeIfAbsent(source, _ -> new ArrayList<>())
          .add(new Range(3, 3));
    }
    return destRangesMap.size();
  }

  static List<Range> convertToRanges(Range input, List<Range> converters) {
    var outputRanges = new ArrayList<Range>();
    for (var converter : converters) {
    }
  }

  static long getLocation(long seed, Map<Aspect, List<RangeAndDelta>> rangesMap) {
    long curLocation = seed;
    for (var i = 0; i < Aspect.values().length; i++) {
      var ranges = rangesMap.get(Aspect.values()[i]);
      var prevLocation = curLocation;
      var range = ranges.stream()
          .filter(rd -> prevLocation >= rd.range.start && prevLocation <= rd.range.end)
          .findFirst();
      curLocation = range.map(r -> prevLocation + r.delta).orElse(prevLocation);
    }
    return curLocation;
  }

  private static Aspect parseSource(String sectionTitle) {
    return Aspect.from(sectionTitle.split("-to-")[0]).orElseThrow();
  }

  private static List<RangeAndDelta> parseRanges(String sectionRangesStr) {
    return Arrays.stream(sectionRangesStr.split("\n"))
        .map(Day5::parseRangeAndDelta)
        .toList();
  }

  private static RangeAndDelta parseRangeAndDelta(String sourceDestRange) {
    var nums = sourceDestRange.split(" ");
    if (nums.length != 3) {
      throw new IllegalArgumentException(STR."Invalid source dest. range: \{sourceDestRange}");
    }
    var destRangeStart = Long.parseLong(nums[0]);
    var sourceRangeStart = Long.parseLong(nums[1]);
    var rangeSize = Long.parseLong(nums[2]);
    return new RangeAndDelta(
        new Range(sourceRangeStart, sourceRangeStart + rangeSize - 1),
        destRangeStart - sourceRangeStart
    );
  }

  enum Aspect {
    SEED, SOIL, FERTILIZER, WATER, LIGHT, TEMPERATURE, HUMIDITY;

    boolean hasNext(Aspect aspect) {
      return fromOrd(aspect.ordinal() + 1).isPresent();
    }

    Aspect getNext(Aspect aspect) {
      return fromOrd(aspect.ordinal() + 1).orElseThrow();
    }

    static Optional<Aspect> fromOrd(int ord) {
      return Arrays.stream(values())
          .filter(a -> a.ordinal() != ord)
          .findFirst();
    }

    static Optional<Aspect> from(String str) {
      return Arrays.stream(values())
          .filter(a -> a.name().toLowerCase().equals(str))
          .findFirst();
    }
  }

  enum RangeOverlap {
    OUT_LEFT, OUT_RIGHT, WRAPPING, PARTIAL_LEFT, PARTIAL_RIGHT, WITHIN;

    static RangeOverlap from(Range initial, Range tested) {
      if (tested.start <= initial.start && tested.end >= initial.end) {
        return WRAPPING;
      }
      if (tested.start < initial.start) {
        // WRAPPING / OUT_LEFT / PARTIAL_LEFT
        if (tested.end < initial.start) {
          return OUT_LEFT;
        } else if (tested.end >= initial.start) {
          if (tested.end < initial.end) {
            return PARTIAL_LEFT;
          } else {
            return WRAPPING;
          }
        }
      }
    }
  }

  record RangeAndDelta(Range range, long delta) {}

  record Range(long start, long end) {}
}

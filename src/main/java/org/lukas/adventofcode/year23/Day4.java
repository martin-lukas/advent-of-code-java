package org.lukas.adventofcode.year23;

import static java.util.stream.Collectors.groupingBy;
import static org.lukas.adventofcode.utils.Logger.log;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.lukas.adventofcode.utils.FileUtils;
import org.lukas.adventofcode.utils.SetUtils;

/**
 * <a href="https://adventofcode.com/2023/day/4">Day 4: Scratchcards</a>
 */
class Day4 {

  public static void main(String[] args) {
    var cardStrs = FileUtils.inputToLines(23, 4);
    var cards = parseCards(cardStrs);
    log.info(STR."Sum of win points: \{sumWinPoints(cards)}");
    log.info(STR."Sum of card copies after processing wins: \{sumCardCopiesAfterWins(cards)}");
  }

  static int sumWinPoints(List<Card> cards) {
    return cards.stream()
        .mapToInt(Day4::getWinCount)
        .map(Day4::getWinScore)
        .sum();
  }

  static int sumCardCopiesAfterWins(List<Card> cards) {
    var copies = cards.stream().collect(groupingBy(Card::id));
    for (var i = 0; i < cards.size(); i++) {
      var cardId = i + 1;
      var winCount = getWinCount(cards.get(i));
      for (var j = 0; j < copies.get(cardId).size(); j++) {
        for (
            var offset = 1;
            offset <= winCount && (i + offset) < cards.size();
            offset++
        ) {
          var copiedCard = cards.get(i + offset);
          copies.computeIfPresent(cardId + offset, (_, v) -> {
            v.add(copiedCard);
            return v;
          });
        }
      }
    }
    return copies.values().stream().mapToInt(List::size).sum();
  }

  static List<Card> parseCards(List<String> cardStrs) {
    return cardStrs.stream()
        .map(Card::from)
        .flatMap(Optional::stream)
        .toList();
  }

  static int getWinScore(int winCount) {
    return switch (winCount) {
      case 0 -> 0;
      case 1 -> 1;
      default -> (int) Math.pow(2, winCount - 1);
    };
  }

  static int getWinCount(Card card) {
    var winningNums = SetUtils.union(card.winNums(), card.tryNums());
    return winningNums.size();
  }

  record Card(int id, Set<Integer> winNums, Set<Integer> tryNums) {

    static final Pattern CARD_STR = Pattern.compile(
        "Card\\s+(\\d+):\\s+([\\d\\s]+)\\s+\\|\\s+([\\d\\s]+)");

    static Optional<Card> from(String cardStr) {
      var matcher = CARD_STR.matcher(cardStr);
      if (matcher.matches()) {
        var id = Integer.parseInt(matcher.group(1));
        var winNums = Arrays.stream(matcher.group(2).split("\\s+"))
            .map(Integer::parseInt)
            .collect(Collectors.toSet());
        var tryNums = Arrays.stream(matcher.group(3).split("\\s+"))
            .map(Integer::parseInt)
            .collect(Collectors.toSet());
        return Optional.of(new Card(id, winNums, tryNums));
      } else {
        return Optional.empty();
      }
    }
  }
}

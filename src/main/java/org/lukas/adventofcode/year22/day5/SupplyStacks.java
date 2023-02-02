package org.lukas.adventofcode.year22.day5;

import org.lukas.adventofcode.utils.FileUtils;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStacks {
    public static void main(String[] args) {
        var lines = FileUtils.inputToLines(22, 5);
        List<LinkedList<Character>> stacks = createStacks(lines.subList(0, 8));
        int[][] moves = createMoves(lines.subList(10, lines.size()));

        for (int[] move : moves) {
            applyMove(stacks, move);
        }

        System.out.print("Top containers for each stack: ");
        stacks.stream()
                .map(LinkedList::getLast)
                .forEach(System.out::print);
        System.out.println();
    }

    static List<LinkedList<Character>> createStacks(List<String> stacksStrs) {
        List<LinkedList<Character>> stacks = List.of(
                new LinkedList<>(), new LinkedList<>(), new LinkedList<>(),
                new LinkedList<>(), new LinkedList<>(), new LinkedList<>(),
                new LinkedList<>(), new LinkedList<>(), new LinkedList<>()
        );
        for (String stacksStr : stacksStrs) {
            for (int col = 0, c = 1;
                 c < stacksStr.length();
                 col++, c += 4
            ) {
                char container = stacksStr.charAt(c);
                if (container != ' ') {
                    stacks.get(col).addFirst(container);
                }
            }
        }
        return stacks;
    }

    static int[][] createMoves(List<String> movesStrs) {
        Pattern movePattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        int[][] moves = new int[movesStrs.size()][3];
        for (int i = 0; i < movesStrs.size(); i++) {
            Matcher moveMatcher = movePattern.matcher(movesStrs.get(i));
            if (moveMatcher.matches()) {
                moves[i] = new int[] {
                        Integer.parseInt(moveMatcher.group(1)),
                        Integer.parseInt(moveMatcher.group(2)),
                        Integer.parseInt(moveMatcher.group(3)),
                };
            }
        }
        return moves;
    }

    static void applyMove(List<LinkedList<Character>> stacks, int[] move) {
        for (int i = 0; i < move[0]; i++) {
            Character container = stacks.get(move[1] - 1).removeLast();
            stacks.get(move[2] - 1).addLast(container);
        }
    }
}
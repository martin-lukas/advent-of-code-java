package org.lukas.adventofcode.year22.day5;

import org.lukas.adventofcode.utils.FileUtils;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SupplyStacks {
    public static void main(String[] args) {
        var lines = FileUtils.inputToLines(22, 5);
        List<int[]> moves = createMoves(lines.subList(10, lines.size()));

        var stacksForSimpleMoves = createStacks(lines.subList(0, 8));
        moves.forEach(move -> applySimpleMove(stacksForSimpleMoves, move));

        var stacksForBatchMoves = createStacks(lines.subList(0, 8));
        moves.forEach(move -> applyBatchMove(stacksForBatchMoves, move));

        printStackTops(stacksForSimpleMoves);
        printStackTops(stacksForBatchMoves);
    }

    static List<Deque<Character>> createStacks(List<String> stacksStrs) {
        List<Deque<Character>> stacks = List.of(
                new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(),
                new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>(),
                new ArrayDeque<>(), new ArrayDeque<>(), new ArrayDeque<>()
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

    static List<int[]> createMoves(List<String> movesStrs) {
        Pattern movePattern = Pattern.compile("move (\\d+) from (\\d+) to (\\d+)");
        List<int[]> moves = new ArrayList<>();
        for (String movesStr : movesStrs) {
            Matcher moveMatcher = movePattern.matcher(movesStr);
            if (moveMatcher.matches()) {
                moves.add(new int[]{
                        Integer.parseInt(moveMatcher.group(1)),
                        Integer.parseInt(moveMatcher.group(2)),
                        Integer.parseInt(moveMatcher.group(3)),
                });
            }
        }
        return moves;
    }

    static void applySimpleMove(List<Deque<Character>> stacks, int[] move) {
        for (int i = 0; i < move[0]; i++) {
            Character container = stacks.get(move[1] - 1).removeLast();
            stacks.get(move[2] - 1).addLast(container);
        }
    }

    static void applyBatchMove(List<Deque<Character>> stacks, int[] move) {
        LinkedList<Character> containers = new LinkedList<>();
        for (int i = 0; i < move[0]; i++) {
            containers.addFirst(stacks.get(move[1] - 1).removeLast());
        }
        stacks.get(move[2] - 1).addAll(containers);
    }

    static void printStackTops(List<Deque<Character>> stacks) {
        System.out.print("Top containers for each stack: ");
        stacks.stream()
                .map(Deque::getLast)
                .forEach(System.out::print);
        System.out.println();
    }
}
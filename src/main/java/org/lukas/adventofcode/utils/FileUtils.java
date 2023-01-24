package org.lukas.adventofcode.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class FileUtils {
    private static final String PREFIX = "src/main/resources/";
    private static final String REL_PATH = "year%d/day%d/input.txt";

    public static List<String> inputToLines(int year, int day) {
        return toLines(REL_PATH.formatted(year, day));
    }

    public static String inputToString(int year, int day) {
        return toString(REL_PATH.formatted(year, day));
    }

    public static List<String> toLines(String relativePath) {
        return toLines(relativePath, false);
    }

    public static String toString(String relativePath) {
        return toString(relativePath, false);
    }

    public static List<String> toLines(String filePath, boolean isAbsolute) {
        try {
            var fullPath = isAbsolute ? filePath : PREFIX + filePath;
            return Files.readAllLines(Paths.get(fullPath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }

    public static String toString(String filePath, boolean isAbsolute) {
        try {
            var fullPath = isAbsolute ? filePath : PREFIX + filePath;
            return Files.readString(Paths.get(fullPath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

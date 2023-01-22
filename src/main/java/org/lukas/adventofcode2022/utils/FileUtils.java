package org.lukas.adventofcode2022.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class FileUtils {
    public static String toString(String filePath) {
        try {
            return Files.readString(Paths.get(filePath));
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}

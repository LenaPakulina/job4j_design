package ru.job4j.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.function.Predicate;

public class Search {
    public static void main(String[] args) throws IOException {
        if (!checkArgs(args)) {
            throw new IllegalArgumentException("There are no necessary parameters.");
        }
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static boolean checkArgs(String[] args) {
        boolean answer = false;
        if (args.length == 2) {
            File dir = new File(args[0]);
            if (dir.exists() && !args[1].isBlank()) {
                answer = true;
            }
        }
        return answer;
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
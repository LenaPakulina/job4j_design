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
        checkArgs(args);
        Path start = Paths.get(args[0]);
        search(start, path -> path.toFile().getName().endsWith(args[1])).forEach(System.out::println);
    }

    private static void checkArgs(String[] args) {
        if (args.length != 2) {
            throw  new IllegalArgumentException("The number of arguments does not meet expectations");
        }
        File dir = new File(args[0]);
        if (!dir.exists()) {
            throw  new IllegalArgumentException("The file search path does not exist");
        }
        if (args[1].isBlank()) {
            throw  new IllegalArgumentException("The extension of files is not specified");
        }
    }

    public static List<Path> search(Path root, Predicate<Path> condition) throws IOException {
        SearchFiles searcher = new SearchFiles(condition);
        Files.walkFileTree(root, searcher);
        return searcher.getPaths();
    }
}
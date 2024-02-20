package ru.job4j.io.duplicates;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class DuplicatesVisitor extends SimpleFileVisitor<Path> {

    private final HashMap<FileProperty, List<Path>> map = new HashMap<>();

    @Override
    public FileVisitResult visitFile(Path file,
                                     BasicFileAttributes attributes) throws IOException {
        FileProperty obj = new FileProperty(attributes.size(), file.getFileName().toString());
        map.computeIfAbsent(obj, list -> new ArrayList<>()).add(file);
        return super.visitFile(file, attributes);
    }

    public void print() {
        map.entrySet()
                .stream()
                .filter(entry -> entry.getValue().size() > 1)
                .forEach(System.out::println);
    }
}

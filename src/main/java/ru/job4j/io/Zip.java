package ru.job4j.io;

import java.io.*;
import java.nio.file.Path;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class Zip {

    private File directory;

    private String exclude;

    private String output;

    private void checkArgs(String[] args) {
        ArgsName argsName = ArgsName.of(args);
        try {
            directory = new File(argsName.get("d"));
            exclude = argsName.get("e");
            output = argsName.get("o");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        if (!directory.exists() || !directory.isDirectory()) {
            throw new IllegalArgumentException("The directory is not corrupted or does not exist");
        }
        if (exclude.length() <= 1 || !exclude.startsWith(".")) {
            throw new IllegalArgumentException("The extension is not valid");
        }
        if (!output.endsWith(".zip")) {
            throw new IllegalArgumentException("The output file extension is not valid");
        }
    }

    public void packFiles(List<Path> source, File target) {
        System.out.println(source);
        System.out.println(target);
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            for (Path path : source) {
                zip.putNextEntry(new ZipEntry(path.toFile().getName()));
                try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(path.toFile()))) {
                    zip.write(output.readAllBytes());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void packSingleFile(File source, File target) {
        try (ZipOutputStream zip = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(target)))) {
            zip.putNextEntry(new ZipEntry(source.getPath()));
            try (BufferedInputStream output = new BufferedInputStream(new FileInputStream(source))) {
                zip.write(output.readAllBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        Zip zip = new Zip();
        zip.packSingleFile(
                new File("./pom.xml"),
                new File("./pom.zip")
        );
        zip.checkArgs(args);
        zip.packFiles(new Search().search(zip.directory.toPath(), path -> !path.endsWith(zip.exclude)), new File(zip.output));
    }
}

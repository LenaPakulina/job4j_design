package ru.job4j.io;

import java.io.*;
import java.util.*;

public class CSVReader {
    private static String path;
    private static String delimiter;
    private static String out;
    private static String filter;

    private static void checkArgs(ArgsName argsName) {
        try {
            path = argsName.get("path");
            delimiter = argsName.get("delimiter");
            out = argsName.get("out");
            filter = argsName.get("filter");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private static List<List<String>> readFiles() {
        List<List<String>> result = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(path))) {
            while (scanner.hasNextLine()) {
                String line  = scanner.nextLine();
                if (line.contains(delimiter)) {
                    result.add(Arrays.asList(line.split(delimiter)));
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private static List<Integer> getColumnIndexes(List<String> headLine) {
        List<Integer> result = new ArrayList<>();
        String[] filterWord = filter.split(",");
        for (String word : filterWord) {
            int index = headLine.indexOf(word);
            if (index == -1) {
                throw new IllegalArgumentException("The data does not match");
            }
            result.add(index);
        }
        return result;
    }

    public static void handle(ArgsName argsName) throws Exception {
        checkArgs(argsName);
        List<List<String>> tables = readFiles();
        if (tables.size() < 2) {
            throw new IllegalArgumentException("The data is not correct");
        }
        List<Integer> indexes = getColumnIndexes(tables.get(0));
        try {
            PrintStream printStream = null;
            if ("stdout".equals(out)) {
                printStream = System.out;
            } else {
                printStream = new PrintStream(new File(out));
            }
            for (List<String> list : tables)  {
                for (Integer index : indexes) {
                    printStream.print(list.get(index));
                    if (!index.equals(indexes.get(indexes.size() - 1))) {
                        printStream.print(delimiter);
                    }
                }
                printStream.print(System.lineSeparator());
            }
            printStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        ArgsName argsName = ArgsName.of(args);
        handle(argsName);
    }
}
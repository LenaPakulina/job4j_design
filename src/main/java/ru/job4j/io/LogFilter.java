package ru.job4j.io;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class LogFilter {
    private final String file;

    public LogFilter(String file) {
        this.file = file;
    }

    private boolean checkLine(String str) {
        String[] subStrs = str.split(" ");
        return subStrs.length > 1 && subStrs[subStrs.length - 2].equals("404");
    }

    public List<String> filter() {
        List<String> ans = new LinkedList<>();
        try (BufferedReader buff = new BufferedReader(new FileReader("data/log.txt"))) {
            ans = buff.lines().filter(this::checkLine).toList();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ans;
    }

    public static void main(String[] args) {
        LogFilter logFilter = new LogFilter("data/log.txt");
        logFilter.filter().forEach(System.out::println);
    }
}

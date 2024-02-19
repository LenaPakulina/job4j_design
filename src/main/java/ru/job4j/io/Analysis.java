package ru.job4j.io;

import java.io.*;
import java.util.LinkedList;
import java.util.List;

public class Analysis {
    public void unavailable(String source, String target) {
        try (BufferedReader reader = new BufferedReader(new FileReader(source));
             PrintWriter print = new PrintWriter(
                     new BufferedOutputStream(new FileOutputStream(target)))) {
            String firstTime = null;
            for (String line = reader.readLine(); line != null; line = reader.readLine()) {
                String[] array = line.split(" ");
                if (array.length > 1) {
                    if (firstTime == null
                            && ("400".equals(array[0]) || "500".equals(array[0]))) {
                        firstTime = array[1];
                    } else if (firstTime != null
                            && ("200".equals(array[0]) || "300".equals(array[0]))) {
                        print.println(String.format("%s;%s;", firstTime, array[1]));
                        firstTime = null;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Analysis analysis = new Analysis();
        analysis.unavailable("data/server.log", "data/target.csv");
        analysis.unavailable("data/server1.log", "data/target1.csv");
    }
}

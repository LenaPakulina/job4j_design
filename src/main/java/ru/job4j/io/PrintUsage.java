package ru.job4j.io;

import java.io.File;
import java.io.FileOutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;

public class PrintUsage {
    public static void main(String[] args) {
        try (PrintStream stream = new PrintStream(new File("data/print.txt"));
             PrintWriter writer = new PrintWriter("data/write.txt")) {
            stream.println("Из PrintStream в FileOutputStream");
            writer.write("New messages");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

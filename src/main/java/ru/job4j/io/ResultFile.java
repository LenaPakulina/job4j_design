package ru.job4j.io;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class ResultFile {
    public static void main(String[] args) {
        try (FileOutputStream stream = new FileOutputStream("data/dataresult.txt")) {
            for (int i = 1; i < 10; i++) {
                stream.write(String.format("1 * %d = %d", i, i).getBytes());
                stream.write(System.lineSeparator().getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

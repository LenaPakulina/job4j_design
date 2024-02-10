package ru.job4j.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class EvenNumberFile {
    public static void main(String[] args) {
        try (FileInputStream stream = new FileInputStream("data/even.txt")) {
            StringBuilder data = new StringBuilder();
            int read = 0;
            while ((read = stream.read()) != -1) {
                data.append((char) read);
            }
            String[] lines = data.toString().split(System.lineSeparator());
            for (String str : lines) {
                int value = Integer.valueOf(str);
                System.out.println(str + " - " + (value % 2 == 0 ? "even" : "not even"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

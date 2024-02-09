package ru.job4j.io;

import java.io.FileInputStream;

public class ReadFile {
    public static void main(String[] args) {
        try (FileInputStream stream = new FileInputStream("data/input.txt")) {
            StringBuilder text = new StringBuilder();
            int read = 0;
            while ((read = stream.read()) != -1) {
                text.append((char) read);
            }
            System.out.println(text);

            String[] array = text.toString().split(System.lineSeparator());
            for (String str : array) {
                System.out.println("line: " + str);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

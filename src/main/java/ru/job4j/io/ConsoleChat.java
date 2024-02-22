package ru.job4j.io;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class ConsoleChat {
    private static final String OUT = "закончить";
    private static final String STOP = "стоп";
    private static final String CONTINUE = "продолжить";
    private final String path;
    private final String botAnswers;
    private final List<String> logs = new ArrayList<>();
    private List<String> answers = new ArrayList<>();

    public ConsoleChat(String path, String botAnswers) {
        this.path = path;
        this.botAnswers = botAnswers;
        answers = readPhrases();
    }

    @SuppressWarnings("checkstyle:InnerAssignment")
    public void run() {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        boolean hasAnswer = true;
        while (scanner.hasNextLine()) {
            logs.add(scanner.nextLine());
            if (OUT.equals(logs.get(logs.size() - 1))) {
                saveLog(logs);
                break;
            } else if (STOP.equals(logs.get(logs.size() - 1))) {
                hasAnswer = false;
            } else if (CONTINUE.equals(logs.get(logs.size() - 1))) {
                hasAnswer = true;
            }
            if (hasAnswer) {
                String autoAnswer = answers.get(rand.nextInt(answers.size()));
                System.out.println(autoAnswer);
                logs.add(autoAnswer);
            }
            if (logs.size() > 5) {
                saveLog(logs);
            }
        }
    }

    private List<String> readPhrases() {
        List<String> result = new ArrayList<>();
        try (BufferedReader input = new BufferedReader(new FileReader(botAnswers))) {
            input.lines().forEach(result::add);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void saveLog(List<String> log) {
        try (PrintWriter out = new PrintWriter(new BufferedOutputStream(new FileOutputStream(path, true)))) {
            logs.forEach(out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }
        logs.clear();
    }

    public static void main(String[] args) {
        ConsoleChat consoleChat = new ConsoleChat("data/ConsoleChatLog.txt",
                "data/ConsoleChatAnswers.txt");
        consoleChat.run();
    }
}

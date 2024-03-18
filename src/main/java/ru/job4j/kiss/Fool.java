package ru.job4j.kiss;

import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Fool {
    private static List<Predicate<Integer>> predicates = Arrays.asList(
            elem -> elem % 3 == 0 && elem % 5 == 0,
            elem -> elem % 3 == 0,
            elem -> elem % 5 == 0
    );

    private static List<String> autoAnswer = Arrays.asList("FizzBuzz", "Fizz", "Buzz");

    private static Scanner input = new Scanner(System.in);

    public static String getStringValue(int value) {
        String result = String.valueOf(value);
        for (int i = 0; i < predicates.size(); i++) {
            if (predicates.get(i).test(value)) {
                result = autoAnswer.get(i);
                break;
            }
        }
        return result;
    }

    public static int userStep(int startAt, String answer) {
        if (!getStringValue(startAt).equals(answer)) {
            System.out.println("Ошибка. Начинай снова.");
            startAt = 0;
        }
        startAt++;
        return startAt;
    }

    public static void main(String[] args) {
        System.out.println("Игра FizzBuzz.");
        var startAt = 1;
        while (startAt < 100) {
            System.out.println(getStringValue(startAt));
            startAt++;
            startAt = userStep(startAt, input.nextLine());
        }
    }
}
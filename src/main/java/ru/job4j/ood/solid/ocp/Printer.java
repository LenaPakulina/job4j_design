package ru.job4j.ood.solid.ocp;

/*
Класс содержит нарушение принципа OCP
1) Т.к. содержит в себе строго заданный тип вывода - System.out
Лучше задать PrintStream (из конструктора, например) определенный класс с реализацией

2) Также, есть нарушение в методе checkDate(), в нем строго заданы условия проверки
 */

import java.io.PrintStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Currency;
import java.util.Date;

public class Printer {
    private final PrintStream sysOut = System.out;

    public void print(Date date) {
        sysOut.println(date);
    }

    public void checkDate(LocalDate date) {
        if (date.getYear() > 2010) {
            sysOut.println(date);
        }
    }
}
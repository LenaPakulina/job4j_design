package ru.job4j.ood.solid.srp.violation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/*
Класс содержит нарушение принципа SRP
Т.к. имеет в методе зашит формат для вывода даты
 */

public class Printer {
    public void print(Date date) {
        DateFormat df = new SimpleDateFormat("dd MMM yyy GG");
        System.out.println(df.format(date));
    }
}

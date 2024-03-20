package ru.job4j.ood.solid.srp.model;

public interface DateTimeParser<T> {
    String parse(T t);
}

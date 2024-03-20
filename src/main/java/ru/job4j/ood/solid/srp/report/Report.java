package ru.job4j.ood.solid.srp.report;

import ru.job4j.ood.solid.srp.model.Employee;

import java.util.function.Predicate;

public interface Report {
    String generate(Predicate<Employee> filter);
}

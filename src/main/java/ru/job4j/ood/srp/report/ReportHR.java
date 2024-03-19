package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.model.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Store;

import java.util.*;
import java.util.function.Predicate;

public class ReportHR implements Report {
    private final Store store;

    private final Comparator comparator;

    public ReportHR(Store store, Comparator comparator) {
        this.store = store;
        this.comparator = comparator;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Salary;")
                .append(System.lineSeparator());
        List<Employee> list = store.findBy(filter);
        list.sort(comparator);
        for (Employee employee : list) {
            text.append(employee.getName()).append(" ")
                    .append(employee.getSalary())
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

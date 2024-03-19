package ru.job4j.ood.srp.report;

import ru.job4j.ood.srp.currency.Currency;
import ru.job4j.ood.srp.currency.CurrencyConverter;
import ru.job4j.ood.srp.model.DateTimeParser;
import ru.job4j.ood.srp.model.Employee;
import ru.job4j.ood.srp.model.Store;

import java.io.PrintStream;
import java.util.Calendar;
import java.util.function.Predicate;

public class ReportDeveloper implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final PrintStream stream;

    public ReportDeveloper(Store store,
                            DateTimeParser<Calendar> dateTimeParser,
                            PrintStream stream) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.stream = stream;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append("; ")
                    .append(dateTimeParser.parse(employee.getHired())).append("; ")
                    .append(dateTimeParser.parse(employee.getFired())).append("; ")
                    .append(employee.getSalary()).append("; ")
                    .append(System.lineSeparator());
        }
        stream.println(text.toString());
        return text.toString();
    }
}

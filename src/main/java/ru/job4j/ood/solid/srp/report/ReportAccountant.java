package ru.job4j.ood.solid.srp.report;

import ru.job4j.ood.solid.srp.currency.Currency;
import ru.job4j.ood.solid.srp.currency.CurrencyConverter;
import ru.job4j.ood.solid.srp.model.DateTimeParser;
import ru.job4j.ood.solid.srp.model.Employee;
import ru.job4j.ood.solid.srp.model.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportAccountant implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;
    private final CurrencyConverter currencyConverter;

    public ReportAccountant(Store store,
                            DateTimeParser<Calendar> dateTimeParser,
                            CurrencyConverter currencyConverter) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
        this.currencyConverter = currencyConverter;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder text = new StringBuilder();
        text.append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator());
        for (Employee employee : store.findBy(filter)) {
            text.append(employee.getName()).append(" ")
                    .append(dateTimeParser.parse(employee.getHired())).append(" ")
                    .append(dateTimeParser.parse(employee.getFired())).append(" ")
                    .append(currencyConverter.convert(Currency.RUB, employee.getSalary(), Currency.USD))
                    .append(System.lineSeparator());
        }
        return text.toString();
    }
}

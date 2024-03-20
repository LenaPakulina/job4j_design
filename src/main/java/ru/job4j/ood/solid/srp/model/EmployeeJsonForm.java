package ru.job4j.ood.solid.srp.model;

import java.util.Calendar;

public class EmployeeJsonForm implements EmployeeOutForm {
    private final DateTimeParser<Calendar> dateTimeParser;

    public EmployeeJsonForm(DateTimeParser<Calendar> dateTimeParser) {
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String toOutForm(Employee employee) {
        return "Name: " + employee.getName()
                + ", Hired: " + dateTimeParser.parse(employee.getHired())
                + ", Fired: " + dateTimeParser.parse(employee.getFired())
                + ", Salary: " + employee.getSalary();
    }
}

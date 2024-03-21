package ru.job4j.ood.solid.srp.report;

import ru.job4j.ood.solid.srp.model.DateTimeParser;
import ru.job4j.ood.solid.srp.model.Employee;
import ru.job4j.ood.solid.srp.model.Store;

import java.util.Calendar;
import java.util.function.Predicate;

public class ReportXml implements Report {
    private final Store store;
    private final DateTimeParser<Calendar> dateTimeParser;

    public ReportXml(Store store, DateTimeParser<Calendar> dateTimeParser) {
        this.store = store;
        this.dateTimeParser = dateTimeParser;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        StringBuilder xml = new StringBuilder();
        xml.append("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"yes\"?>\n");
        xml.append("<employees>\n");
        for (Employee employee : store.findBy(filter)) {
            xml.append("    <employee>\n")
                    .append("        <fired>").append(dateTimeParser.parse(employee.getFired())).append("</fired>\n")
                    .append("        <hired>").append(dateTimeParser.parse(employee.getHired())).append("</hired>\n")
                    .append("        <name>").append(employee.getName()).append("</name>\n")
                    .append("        <salary>").append(employee.getSalary()).append("</salary>\n")
                    .append("    </employee>\n");
        }
        xml.append("</employees>\n");
        return xml.toString();
    }
}

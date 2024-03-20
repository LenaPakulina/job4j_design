package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.solid.srp.model.DateTimeParser;
import ru.job4j.ood.solid.srp.model.Employee;
import ru.job4j.ood.solid.srp.model.MemoryStore;
import ru.job4j.ood.solid.srp.model.ReportDateTimeParser;
import ru.job4j.ood.solid.srp.report.Report;
import ru.job4j.ood.solid.srp.report.ReportDeveloper;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportDeveloperTest {
    @Test
    public void checkGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker = new Employee("Ivan", now, now, 100);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker);
        Report engine = new ReportDeveloper(store, parser, System.out);
        StringBuilder expected = new StringBuilder()
                .append("Name; Hired; Fired; Salary;")
                .append(System.lineSeparator())
                .append(worker.getName()).append("; ")
                .append(parser.parse(worker.getHired())).append("; ")
                .append(parser.parse(worker.getFired())).append("; ")
                .append(worker.getSalary()).append("; ")
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}
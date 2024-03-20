package ru.job4j.ood.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.solid.srp.model.DateTimeParser;
import ru.job4j.ood.solid.srp.model.Employee;
import ru.job4j.ood.solid.srp.model.MemoryStore;
import ru.job4j.ood.solid.srp.model.ReportDateTimeParser;
import ru.job4j.ood.solid.srp.report.Report;
import ru.job4j.ood.solid.srp.report.ReportHR;
import ru.job4j.ood.solid.srp.report.SalaryDescComparator;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportHRTest {
    @Test
    public void checkGenerated() {
        MemoryStore store = new MemoryStore();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vitya", now, now, 500);
        DateTimeParser<Calendar> parser = new ReportDateTimeParser();
        store.add(worker1);
        store.add(worker2);
        Report engine = new ReportHR(store, new SalaryDescComparator());
        StringBuilder expected = new StringBuilder()
                .append("Name; Salary;")
                .append(System.lineSeparator())
                .append(worker2.getName()).append(" ")
                .append(worker2.getSalary())
                .append(System.lineSeparator());
        expected.append(worker1.getName()).append(" ")
                .append(worker1.getSalary())
                .append(System.lineSeparator());
        assertThat(engine.generate(employee -> true)).isEqualTo(expected.toString());
    }
}
package ru.job4j.ood.solid.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.solid.srp.model.*;

import java.util.Calendar;

import static org.assertj.core.api.Assertions.*;

class ReportJsonTest {
    @Test
    void testGenerate() {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser = new ReportDateTimeParser();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vitya", now, now, 500);
        store.add(worker1);
        store.add(worker2);
        ReportJson jsonReport = new ReportJson(store, new EmployeeJsonForm(dateTimeParser));
        String json = jsonReport.generate(employee -> true);
        System.out.println(json);
        assertThat(json).contains("Ivan", "Vitya");
    }
}
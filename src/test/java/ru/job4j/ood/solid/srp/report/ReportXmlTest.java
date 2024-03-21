package ru.job4j.ood.solid.srp.report;

import org.junit.jupiter.api.Test;
import ru.job4j.ood.solid.srp.model.*;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.IOException;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

class ReportXmlTest {
    @Test
    void testGenerate() {
        Store store = new MemoryStore();
        DateTimeParser<Calendar> dateTimeParser = new ReportXmlParser();
        Calendar now = Calendar.getInstance();
        Employee worker1 = new Employee("Ivan", now, now, 100);
        Employee worker2 = new Employee("Vitya", now, now, 500);
        store.add(worker1);
        store.add(worker2);
        ReportXml xmlReport = new ReportXml(store, dateTimeParser);
        String xmlStrReport = xmlReport.generate(employee -> true);
        String xmlExpected = "";
        List<Employee> employees = new ArrayList<>(List.of(worker1, worker2));
        try {
            JAXBContext context = JAXBContext.newInstance(Employees.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            try (StringWriter writer = new StringWriter()) {
                marshaller.marshal(new Employees(employees), writer);
                xmlExpected = writer.getBuffer().toString();
            }
        } catch (JAXBException | IOException e) {
            e.printStackTrace();
        }
        assertThat(xmlExpected).isEqualTo(xmlStrReport);
    }
}
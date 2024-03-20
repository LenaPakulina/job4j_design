package ru.job4j.ood.solid.srp.report;

import com.google.gson.Gson;
import ru.job4j.ood.solid.srp.model.DateTimeParser;
import ru.job4j.ood.solid.srp.model.Employee;
import ru.job4j.ood.solid.srp.model.EmployeeOutForm;
import ru.job4j.ood.solid.srp.model.Store;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.function.Predicate;

public class ReportJson implements Report {
    private final Store store;
    private final Gson gson;
    private final EmployeeOutForm employeeForm;

    public ReportJson(Store store, EmployeeOutForm employeeForm) {
        this.store = store;
        this.gson = new Gson();
        this.employeeForm = employeeForm;
    }

    @Override
    public String generate(Predicate<Employee> filter) {
        List<Employee> filteredEmployees = store.findBy(filter);
        List<String> formattedEmployees = new ArrayList<>();
        for (Employee employee : filteredEmployees) {
            formattedEmployees.add(employeeForm.toOutForm(employee));
        }
        return gson.toJson(formattedEmployees);
    }
}

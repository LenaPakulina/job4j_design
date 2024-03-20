package ru.job4j.ood.solid.srp.report;

import ru.job4j.ood.solid.srp.model.Employee;

import java.util.Comparator;

public class SalaryDescComparator implements Comparator<Employee> {
    @Override
    public int compare(Employee o1, Employee o2) {
        return Double.compare(o2.getSalary(), o1.getSalary());
    }
}

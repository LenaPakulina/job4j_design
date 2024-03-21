package ru.job4j.ood.solid.srp.model;

import com.sun.xml.txw2.annotation.XmlElement;

import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "employees")
public class Employees {
    private List<Employee> employeesList;

    public Employees(List<Employee> employees) {
        this.employeesList = employees;
    }

    public Employees() {
    }

    public List<Employee> getEmployee() {
        return employeesList;
    }

    public void setEmployee(List<Employee> users) {
        this.employeesList = users;
    }
}



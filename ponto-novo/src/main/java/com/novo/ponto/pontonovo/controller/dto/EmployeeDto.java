package com.novo.ponto.pontonovo.controller.dto;

import com.novo.ponto.pontonovo.model.Employee;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EmployeeDto {
    private String id;
    private String name;
    private LocalDateTime extraTime;
    private LocalDateTime timeToBeCompensated;

    public EmployeeDto(Employee employee) {
        this.id = employee.getId();
        this.name = employee.getName();
        this.extraTime = employee.getExtraTime();
        this.timeToBeCompensated = employee.getTimeToBeCompensated();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getExtraTime() {
        return extraTime;
    }

    public LocalDateTime getTimeToBeCompensated() {
        return timeToBeCompensated;
    }

    public static List<EmployeeDto> converter(List<Employee> employeeList) {
        return employeeList.stream().map(EmployeeDto::new).collect(Collectors.toList());
    }
}

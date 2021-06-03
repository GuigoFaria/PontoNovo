package com.novo.ponto.pontonovo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
@Document
public class BusinessDay {
    @Id
    private String id;
    private List<InJob> inJobList;
    private List<OutJob> outJobList;
    private Employee employee;
    private LocalDate dateWork;
    private LocalDateTime workTime;

    public String getId() {
        return id;
    }

    public List<InJob> getInJobList() {
        return inJobList;
    }

    public void setInJobList(List<InJob> inJobList) {
        this.inJobList = inJobList;
    }

    public List<OutJob> getOutJobList() {
        return outJobList;
    }

    public void setOutJobList(List<OutJob> outJobList) {
        this.outJobList = outJobList;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public void setDateWork(LocalDate dateWork) {
        this.dateWork = dateWork;
    }

    public LocalDateTime getWorkTime() {
        return workTime;
    }

    public void setWorkTime(LocalDateTime workTime) {
        this.workTime = workTime;
    }
}

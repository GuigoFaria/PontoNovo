package com.novo.ponto.pontonovo.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.util.UUID;
@Document
public class Employee {
    @Id
    private String id;
    private String name;
    private LocalDateTime extraTime;
    private LocalDateTime timeToBeCompensated;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getExtraTime() {
        return extraTime;
    }

    public void setExtraTime(LocalDateTime extraTime) {
        this.extraTime = extraTime;
    }

    public LocalDateTime getTimeToBeCompensated() {
        return timeToBeCompensated;
    }

    public void setTimeToBeCompensated(LocalDateTime timeToBeCompensated) {
        this.timeToBeCompensated = timeToBeCompensated;
    }
}

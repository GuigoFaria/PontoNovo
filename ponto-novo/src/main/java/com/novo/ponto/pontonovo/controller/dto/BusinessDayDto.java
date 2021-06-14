package com.novo.ponto.pontonovo.controller.dto;

import com.novo.ponto.pontonovo.model.BusinessDay;
import com.novo.ponto.pontonovo.model.InJob;
import com.novo.ponto.pontonovo.model.OutJob;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class BusinessDayDto {
    private String id;
    private List<InJob> inJobList;
    private List<OutJob> outJobList;
    private LocalDate dateWork;
    private LocalDateTime workTime;

    public BusinessDayDto(BusinessDay day) {
        this.id = day.getId();
        this.inJobList = day.getInJobList();
        this.outJobList = day.getOutJobList();
        this.dateWork = day.getDateWork();
        this.workTime = day.getWorkTime();
    }

    public BusinessDayDto() {

    }

    public String getId() {
        return id;
    }

    public List<InJob> getInJobList() {
        return inJobList;
    }

    public List<OutJob> getOutJobList() {
        return outJobList;
    }

    public LocalDate getDateWork() {
        return dateWork;
    }

    public LocalDateTime getWorkTime() {
        return workTime;
    }

    public static List<BusinessDayDto> converter(List<BusinessDay> businessDayList) {
        return businessDayList.stream().map(BusinessDayDto::new).collect(Collectors.toList());
    }
}

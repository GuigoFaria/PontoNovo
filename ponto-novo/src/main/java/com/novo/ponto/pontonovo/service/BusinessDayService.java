package com.novo.ponto.pontonovo.service;

import com.novo.ponto.pontonovo.form.BusinessDayForm;
import com.novo.ponto.pontonovo.model.BusinessDay;
import com.novo.ponto.pontonovo.model.Employee;
import com.novo.ponto.pontonovo.model.InJob;
import com.novo.ponto.pontonovo.repository.BusinessDayRepository;
import com.novo.ponto.pontonovo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BusinessDayService {
    @Autowired
    BusinessDayRepository businessDayRepository;
    @Autowired
    EmployeeRepository employeeRepository;

    public Optional<BusinessDay> createANewDay(BusinessDayForm businessDayForm) throws Exception {
        Optional<Employee> employee = employeeRepository.findById(businessDayForm.getIdEmployee());
        Optional<BusinessDay> optionalBusinessDay = Optional.empty();

        BusinessDay businessDay = new BusinessDay();
        if (employee.isPresent()) {
            Optional<BusinessDay> today = businessDayRepository.findByDateWork(LocalDate.now());
            if (today.isEmpty()) {
                optionalBusinessDay = Optional.of(iniciateDay(businessDay, employee.get()));
            } else {
                throw new Exception("Dia já existe");
            }
        }

        return optionalBusinessDay;
    }

    private BusinessDay iniciateDay(BusinessDay businessDay, Employee employee) {
        List<InJob> inJobList = new ArrayList<>();
        InJob inJob = new InJob(LocalDateTime.now());
        inJobList.add(inJob);
        businessDay.setInJobList(inJobList);
        businessDay.setEmployee(employee);
        businessDay.setDateWork(LocalDate.now());
        return businessDay;
    }
}

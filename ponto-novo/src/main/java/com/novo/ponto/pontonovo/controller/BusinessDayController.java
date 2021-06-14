package com.novo.ponto.pontonovo.controller;


import com.novo.ponto.pontonovo.controller.dto.BusinessDayDto;
import com.novo.ponto.pontonovo.form.BusinessDayForm;
import com.novo.ponto.pontonovo.model.BusinessDay;
import com.novo.ponto.pontonovo.model.Employee;
import com.novo.ponto.pontonovo.repository.BusinessDayRepository;
import com.novo.ponto.pontonovo.repository.EmployeeRepository;
import com.novo.ponto.pontonovo.service.BusinessDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/day")
public class BusinessDayController {
    @Autowired
    private BusinessDayRepository businessDayRepository;
    @Autowired
    private BusinessDayService businessDayService;

    @PostMapping
    @Transactional
    public ResponseEntity<BusinessDay> createADayJob(@RequestBody BusinessDayForm businessDayForm, UriComponentsBuilder uriBuilder) {
        BusinessDay businessDay = new BusinessDay();
        try {
            Optional<BusinessDay> day = businessDayService.createANewDay(businessDayForm);
            if (day.isEmpty()) {
                return ResponseEntity.notFound().build();
            }

            businessDay = businessDayRepository.save(day.get());

        } catch (Exception e) {
            return ResponseEntity.status(422).build();
        }
        URI uri = uriBuilder.path("/day/{id}").buildAndExpand(businessDay.getId()).toUri();
        return ResponseEntity.created(uri).body(businessDay);
    }

    @GetMapping("/employee/{idEmployee}")
    public ResponseEntity<List<BusinessDayDto>> returnAllDays(@PathVariable String idEmployee) {
        List<BusinessDay> businessDays = businessDayService.returnListFromEmployee(idEmployee);
        if (businessDays == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(new BusinessDayDto().converter(businessDays));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BusinessDay> findDay(@PathVariable String id) {
        Optional<BusinessDay> businessDay = businessDayRepository.findById(id);
        if (businessDay.isPresent()) {
            return ResponseEntity.ok(businessDay.get());
        }
        return ResponseEntity.notFound().build();
    }
}

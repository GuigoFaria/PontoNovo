package com.novo.ponto.pontonovo.controller;


import com.novo.ponto.pontonovo.form.BusinessDayForm;
import com.novo.ponto.pontonovo.model.BusinessDay;
import com.novo.ponto.pontonovo.repository.BusinessDayRepository;
import com.novo.ponto.pontonovo.service.BusinessDayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<BusinessDay> createADayJob(@RequestBody BusinessDayForm businessDayForm) {
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

        return ResponseEntity.ok(businessDay);

    }
}

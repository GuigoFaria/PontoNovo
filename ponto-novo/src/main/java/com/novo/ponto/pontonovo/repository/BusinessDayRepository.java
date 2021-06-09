package com.novo.ponto.pontonovo.repository;

import com.novo.ponto.pontonovo.model.BusinessDay;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface BusinessDayRepository extends MongoRepository<BusinessDay, String> {
    Optional<BusinessDay> findByDateWork(LocalDate date);
}

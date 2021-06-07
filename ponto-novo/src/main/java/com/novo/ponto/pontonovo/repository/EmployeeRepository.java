package com.novo.ponto.pontonovo.repository;

import com.novo.ponto.pontonovo.model.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {

}

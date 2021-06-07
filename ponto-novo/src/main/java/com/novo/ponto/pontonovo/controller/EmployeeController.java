package com.novo.ponto.pontonovo.controller;

import com.novo.ponto.pontonovo.controller.dto.EmployeeDto;
import com.novo.ponto.pontonovo.model.Employee;
import com.novo.ponto.pontonovo.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    EmployeeRepository employeeRepository;

    @GetMapping
    public List<EmployeeDto> listAll() {
        List<Employee> employeeList = employeeRepository.findAll();
        return EmployeeDto.converter(employeeList);
    }

    @PostMapping
    @Transactional
    public ResponseEntity<EmployeeDto> insertEmployee(@RequestBody Employee employee, UriComponentsBuilder uriBuilder) {
        employeeRepository.save(employee);
        URI uri = uriBuilder.path("/employee/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).body(new EmployeeDto(employee));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable String id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            employeeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeDto> employeeById(@PathVariable String id){
        Optional<Employee> employeeOptional = employeeRepository.findById(id);
        if(employeeOptional.isPresent()){
            Employee employee = employeeOptional.get();
            return ResponseEntity.ok().body(new EmployeeDto(employee));
        }
        return ResponseEntity.notFound().build();
    }
}


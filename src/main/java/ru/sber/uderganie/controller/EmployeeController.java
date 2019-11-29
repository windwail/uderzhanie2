package ru.sber.uderganie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.uderganie.model.Employee;
import ru.sber.uderganie.repository.EmployeeRepository;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @GetMapping("/employee")
    ResponseEntity<List<Employee>> getEmployee() {
        return ResponseEntity.ok(repository.findAll());
    }
}

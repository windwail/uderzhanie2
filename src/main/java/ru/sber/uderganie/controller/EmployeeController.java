package ru.sber.uderganie.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sber.uderganie.model.Claim;
import ru.sber.uderganie.model.Employee;
import ru.sber.uderganie.repository.ClaimRepository;
import ru.sber.uderganie.repository.EmployeeRepository;

import java.util.List;

@RestController
public class EmployeeController {

    @Autowired
    EmployeeRepository repository;

    @Autowired
    ClaimRepository claimRepository;

    @GetMapping("/employee")
    ResponseEntity<List<Employee>> getEmployee() {
        return ResponseEntity.ok(repository.findAll());
    }

    @GetMapping("/claims")
    ResponseEntity<List<Claim>> getClaims() {
        return ResponseEntity.ok(claimRepository.findAll());
    }

    @PostMapping("/claim")
    ResponseEntity<Claim> putClaim(@RequestBody Claim claim) {
        return ResponseEntity.ok(claimRepository.save(claim));
    }
}

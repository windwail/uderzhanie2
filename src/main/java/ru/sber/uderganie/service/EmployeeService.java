package ru.sber.uderganie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.sber.uderganie.model.Employee;
import ru.sber.uderganie.repository.EmployeeRepository;

@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository repository;

    Employee findEmployee(String employeeId) {
        return repository.findByEmployeeId(employeeId);
    }
}

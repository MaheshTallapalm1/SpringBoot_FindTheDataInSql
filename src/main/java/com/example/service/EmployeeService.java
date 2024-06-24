package com.example.service;

//EmployeeService.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.Employee;
import com.example.repo.EmployeeRepo;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

 @Autowired
 private EmployeeRepo employeeRepository;

 public List<Employee> getAllEmployees() {
     return employeeRepository.findAll();
 }

 public Optional<Employee> getEmployeeById(Long id) {
     return employeeRepository.findById(id);
 }

 public Employee createEmployee(Employee employee) {
     return employeeRepository.save(employee);
 }

 public void deleteEmployee(Long id) {
     employeeRepository.deleteById(id);
 }
}


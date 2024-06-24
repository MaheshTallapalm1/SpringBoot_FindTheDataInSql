package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.exception.ResuorceNotFoundException;
import com.example.model.Employee;
import com.example.repo.EmployeeRepo;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeController {
	
	@Autowired
	private EmployeeRepo empRepo;
	
	@GetMapping
	public List<Employee>getAllEmployee(){
		return empRepo.findAll();
	}
	@PostMapping()
	public Employee createEmployee(@RequestBody Employee emp) {
		return empRepo.save(emp);
		
	}
	// GET method to fetch an employee by ID
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable long id) {
        Employee emp = empRepo.findById(id)
                .orElseThrow(() -> new ResuorceNotFoundException("Employee not found with id: " + id));
        return ResponseEntity.ok(emp);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable long id,@RequestBody Employee empdil){
    	Employee emp=empRepo.findById(id)
    			.orElseThrow(() -> new ResuorceNotFoundException("Employee not found with id: " + id));
    			emp.setFirstName(empdil.getFirstName());
    			emp.setLastName(empdil.getLastName());
    			emp.setEmail(empdil.getEmail());
    			
    			empRepo.save(emp);
    			
    			return ResponseEntity.ok(emp);
    	
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteEmployee(@PathVariable long id){
    	Employee emp=empRepo.findById(id)
    			.orElseThrow(() -> new ResuorceNotFoundException("Employee not found with id: " + id));
    	empRepo.delete(emp);
    	
		return new ResponseEntity<>(HttpStatus.NO_CONTENT) ;
    	
    }
    

}

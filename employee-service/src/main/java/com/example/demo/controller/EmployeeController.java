package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.demo.client.entities.Laptop;
import com.example.demo.dto.EmployeeWithLaptopsDTO;
import com.example.demo.dto.LaptopDTO;
import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;


@RestController
@RequestMapping("/api/employees")
public class EmployeeController {
	
	@Autowired
	EmployeeService EmployeeService;
	
	@PostMapping("/addEmployee")
	public boolean addEmployee(@RequestBody Employee employee)
	{
		return EmployeeService.addEmployee(employee);
	}
	@GetMapping("/{id}")
	public Employee getEmployee(@PathVariable int id)
	{
		return EmployeeService.getEmployee(id);
	}	
	
	@GetMapping("/employee/{employeeId}/laptops")
    public ResponseEntity<EmployeeWithLaptopsDTO> getLaptopsForEmployee(@PathVariable int employeeId) {
		return ResponseEntity.ok(EmployeeService.getEmployeeWithLaptops(employeeId));
    }
	
	@RequestMapping(value="/updateEmployee/{id}", method=RequestMethod.PUT)
	public Employee updateEmployee(@PathVariable int id, @RequestBody Employee employee) {
		
		return EmployeeService.updateEmployee(id, employee);
	}
	
	@RequestMapping(value="/deleteEmployee/{id}", method=RequestMethod.DELETE)
	public boolean removeEmployee(@PathVariable int id) {
		
		return EmployeeService.removeEmployee(id);
	}
	
	
	@RequestMapping(value="/get/allEmployees", method=RequestMethod.GET)
	public List<Employee> getAllEmployees() {
		
		return EmployeeService.getAllEmployees();
	}

}

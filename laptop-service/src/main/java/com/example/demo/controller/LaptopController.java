package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Laptop;
import com.example.demo.model.LaptopAssignmentDTO;
import com.example.demo.service.LaptopService;

@RestController
@RequestMapping("/api/laptops")
public class LaptopController {
	
	@Autowired
	LaptopService laptopService;
	
	@PostMapping(value="/addLaptop")
	public boolean addLaptop(@RequestBody Laptop laptop)
	{
		System.out.println(laptop);
		return laptopService.addLaptop(laptop);
	}
	@GetMapping("/getLaptop/{id}")
    public Laptop getLaptop(@PathVariable int id) {
        return laptopService.getLaptop(id);
    }
	
	@GetMapping("/by-employee/{employeeId}")
    public List<Laptop> getLaptopsByEmployee(@PathVariable int employeeId) {
        return laptopService.getLaptopsByEmployee(employeeId);
    }
	
	@RequestMapping(value="/updateLaptop/{id}", method=RequestMethod.PUT)
	public Laptop updateLaptop(@PathVariable int id, @RequestBody Laptop laptop) {
		
		return laptopService.updateLaptop(id, laptop);
	}
	
	@RequestMapping(value="/deleteLaptop/{id}", method=RequestMethod.DELETE)
	public boolean removeLaptop(@PathVariable int id) {
		
		return laptopService.removeLaptop(id);
	}
	
	@RequestMapping(value="/get/allLaptops", method=RequestMethod.GET)
	public List<Laptop> getAllLaptops() {
		
		return laptopService.getAllLaptops();
	}
	
	@PostMapping("/assign")
	public ResponseEntity<String> assignLaptopToEmployee(@RequestBody LaptopAssignmentDTO dto) {
		
		laptopService.assignLaptopToEmployee(dto);
	    
	    return ResponseEntity.ok("Assignment message sent");
	}
}

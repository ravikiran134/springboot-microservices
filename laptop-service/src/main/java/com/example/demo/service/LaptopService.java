package com.example.demo.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;


import com.example.demo.messaging.MessageProducer;
import com.example.demo.model.Laptop;
import com.example.demo.model.LaptopAssignmentDTO;
import com.example.demo.repository.LaptopRepository;

@Service
public class LaptopService {
	
	@Autowired
	LaptopRepository laptopRepo;
	
	@Autowired
	MessageProducer messageProducer;
	
	public boolean addLaptop(Laptop laptop)
	{
		List<Laptop> laptoptList = (List<Laptop>) laptopRepo.findAll();
		for(Laptop s: laptoptList)
		{
			if(s.getBrand()==laptop.getBrand())
			{
				return false;
			}
			
		}
		laptopRepo.save(laptop);
		return true;
	}
	
	public Laptop getLaptop(int id)
	{
		Laptop s= null;
		Optional<Laptop> optional = laptopRepo.findById(id);
		if(optional.isPresent()) {
			
			s = optional.get();
			System.out.println(s.toString());
		}
		return s;
	}
	public List<Laptop> getLaptopsByEmployee(int employeeId) {
        return laptopRepo.findByEmployeeId(employeeId);
    }
	public Laptop updateLaptop(int id, Laptop employee) {
		
		Optional<Laptop> optional = laptopRepo.findById(id);
		
		if(optional.isPresent()) {
			
			removeLaptop(id);
		}
		addLaptop(employee);
		return employee;
	}

	public boolean removeLaptop(int id) {
		
		boolean flag = false;
		Optional<Laptop> optional = laptopRepo.findById(id);
		
		if(optional.isPresent()) {
			
			laptopRepo.deleteById(id);
			flag = true;
		}
		return flag;
	}
	
	public List<Laptop> getAllLaptops()
	{
		return (List<Laptop>) laptopRepo.findAll();
	}

	public ResponseEntity<String> assignLaptopToEmployee(LaptopAssignmentDTO dto) {
		
		messageProducer.sendLaptopAssignment(dto);
	    return ResponseEntity.ok("Assignment message sent");
	}

	

}

package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.client.entities.Laptop;
import com.example.demo.dto.EmployeeWithLaptopsDTO;
import com.example.demo.dto.LaptopAssignmentDTO;
import com.example.demo.dto.LaptopDTO;
//import com.example.demo.clients.LaptopClient;
import com.example.demo.model.Employee;

import com.example.demo.repository.EmployeeRepository;
import com.example.demo.repository.LaptopRepository;






@Service
public class EmployeeService {
	
	@Autowired
	EmployeeRepository employeeRepo;
	
	@Autowired
	LaptopRepository laptopRepo;
	
	@Autowired
	private WebClient.Builder webClientBuilder;
	
//	@Autowired
//	LaptopClient laptopClient;
	
	public boolean addEmployee(Employee employee)
	{
		List<Employee> employeetList = (List<Employee>) employeeRepo.findAll();
		for(Employee s: employeetList)
		{
			if(s.getId()==employee.getId())
			{
				return false;
			}
			
		}
		employeeRepo.save(employee);
		return true;
	}
	
	
	
	public Employee getEmployee(int id)
	{
		Employee s= null;
		Optional<Employee> optional = employeeRepo.findById(id);
		if(optional.isPresent()) {
			
			s = optional.get();
			
		}
		return s;
	}
	
//	public List<Laptop> getEmployeeWithLaptop(int id)
//	{
//		Employee s= null;
//		List<Laptop> laptops = new ArrayList<>();
//		Optional<Employee> optional = employeeRepo.findById(id);
//		if(optional.isPresent()) {
//			
//			s = optional.get();
//			
//			 //laptops.addAll((List<Laptop>) laptopClient.getLaptopByEmployee(s.getId()));
//			
//			System.out.println(laptopClient.getLaptopByEmployee(s.getId()));
//		}
//		System.out.println(laptops.get(0));
//		return laptops;
//	}
	
	public EmployeeWithLaptopsDTO getEmployeeWithLaptops(int employeeId) {
	    Employee emp = employeeRepo.findById(employeeId)
	            .orElseThrow(() -> new RuntimeException("Employee not found"));

	    List<LaptopDTO> laptops = webClientBuilder.build()
	            .get()
	            .uri("http://laptop-service/api/laptops/by-employee/" + employeeId)
	            .retrieve()
	            .bodyToFlux(LaptopDTO.class)
	            .collectList()
	            .block(); // Optional: Replace with reactive Mono later

	    return new EmployeeWithLaptopsDTO(emp.getId(), emp.getName(), laptops);
	}
	
	public Employee updateEmployee(int id, Employee employee) {
		
		Optional<Employee> optional = employeeRepo.findById(id);
		
		if(optional.isPresent()) {
			
			removeEmployee(id);
		}
		addEmployee(employee);
		return employee;
	}

	public boolean removeEmployee(int id) {
		
		boolean flag = false;
		Optional<Employee> optional = employeeRepo.findById(id);
		
		if(optional.isPresent()) {
			
			employeeRepo.deleteById(id);
			flag = true;
		}
		return flag;
	}
	
	
	public List<Employee> getAllEmployees()
	{
		return (List<Employee>) employeeRepo.findAll();
	}



	public void assignLaptop(LaptopAssignmentDTO dto) {
	    Employee employee = employeeRepo.findById(dto.getEmployeeId())
	        .orElseThrow(() -> new RuntimeException("Employee not found"));

	    Laptop laptop = laptopRepo.findById(dto.getLaptopId())
	    	    .orElseThrow(() -> new RuntimeException("Laptop not found"));
	        
	    laptop.setEmployeeId(employee.getId());
	    
	    laptopRepo.save(laptop);
	}
}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.example.demo.model.Laptop;

public interface LaptopRepository extends CrudRepository<Laptop, Integer> {

	List<Laptop> findByEmployeeId(int employeeId);

}

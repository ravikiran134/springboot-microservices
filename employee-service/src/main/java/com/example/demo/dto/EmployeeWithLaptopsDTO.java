package com.example.demo.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeWithLaptopsDTO {
    private int id;
    private String name;
    private List<LaptopDTO> laptops;
}

package com.example.demo.service;

import java.util.List;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.example.demo.client.entities.Laptop;
import com.example.demo.dto.LaptopAssignmentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;

@Service
public class LaptopClientService {

    private final WebClient webClient;
    private final ObjectMapper objectMapper = new ObjectMapper();
    
    @Autowired
    EmployeeService employeeService;

    public LaptopClientService(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.build();
    }

    public List<Laptop> getLaptopsByEmployeeId(int employeeId) {
        return webClient.get()
                .uri("http://laptop-service/laptops/by-employee/" + employeeId)
                .retrieve()
                .bodyToFlux(Laptop.class)
                .collectList()
                .block();  // or use reactive if needed
    }
    
    @RabbitListener(queues = "${rabbitmq.queue}")
    public void receiveMessage(String messageJson) {
        try {
        	
            LaptopAssignmentDTO dto = objectMapper.readValue(messageJson, LaptopAssignmentDTO.class);
            employeeService.assignLaptop(dto);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

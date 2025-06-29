package com.example.demo.messaging;


import com.example.demo.model.LaptopAssignmentDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class MessageProducer {

    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    public MessageProducer(RabbitTemplate rabbitTemplate, ObjectMapper objectMapper) {
        this.rabbitTemplate = rabbitTemplate;
        this.objectMapper = objectMapper;
    }

    public void sendLaptopAssignment(LaptopAssignmentDTO dto) {
        try {
            String jsonMessage = objectMapper.writeValueAsString(dto);
            rabbitTemplate.convertAndSend(exchange, routingKey, jsonMessage);
            System.out.println("Message sent: " + jsonMessage);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
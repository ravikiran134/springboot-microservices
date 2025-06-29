package com.example.demo.messaging;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    @Value("${rabbitmq.queue}")
    private String queue;

    @Value("${rabbitmq.exchange}")
    private String exchange;

    @Value("${rabbitmq.routingkey}")
    private String routingKey;

    @Bean
    public Queue laptopQueue() {
        return new Queue(queue);
    }

    @Bean
    public TopicExchange laptopExchange() {
        return new TopicExchange(exchange);
    }

    @Bean
    public Binding binding(Queue laptopQueue, TopicExchange laptopExchange) {
        return BindingBuilder
                .bind(laptopQueue)
                .to(laptopExchange)
                .with(routingKey);
    }
}
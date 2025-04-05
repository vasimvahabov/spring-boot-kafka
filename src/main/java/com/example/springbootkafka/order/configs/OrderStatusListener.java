package com.example.springbootkafka.order.configs;

import com.example.springbootkafka.order.dtos.OrderResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;

@Slf4j
@Configuration
public class OrderStatusListener {

    @KafkaListener(topics = "order.status.updates", clientIdPrefix = "String")
    public void listenOrderStatus(ConsumerRecord<String, Object> record,
                                  @Payload OrderResponse payload) {
        log.info("Received key : {}", record.key());
        log.info("Received record : {}", record);
        log.info("Received order : {}", payload);
    }
}

package com.example.springbootkafka.order.configs;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.apache.kafka.clients.admin.AdminClientConfig;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.core.KafkaAdmin;

@Getter
@Configuration
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class OrderStatusKafkaConfig {

    KafkaProperties kafkaProperties;
    String topic;
    String bootstrapServers;
    String consumerGroupId;

    public OrderStatusKafkaConfig(KafkaProperties kafkaProperties,
                                  @Value("${kafka.topics.order-status-updates}") String topic,
                                  @Value("${spring.kafka.bootstrap-servers}") String bootstrapServers,
                                  @Value("${spring.kafka.consumer.group-id}") String consumerGroupId){
        this.kafkaProperties = kafkaProperties;
        this.topic = topic;
        this.bootstrapServers = bootstrapServers;
        this.consumerGroupId = consumerGroupId;
    }

    @Bean
    KafkaAdmin admin() {
        var properties = kafkaProperties.buildAdminProperties(null);
        properties.put(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProperties.getBootstrapServers());
        return new KafkaAdmin(properties);
    }

    @Bean
    NewTopic topic() {
        return new NewTopic(topic, 3, (short) 3);
    }

}

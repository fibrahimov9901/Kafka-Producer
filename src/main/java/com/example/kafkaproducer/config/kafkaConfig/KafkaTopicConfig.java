package com.example.kafkaproducer.config.kafkaConfig;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaTopicConfig {


    @Value("${app.kafka.producer.topic}")
    private String topicName;

    @Bean
    public NewTopic newTopic(){
        return TopicBuilder
                .name(topicName)
                .partitions(5)
                .compact() // duplicate olanlari buraxmasin
                .replicas(1)
                .build();
    }
}

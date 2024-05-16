package com.example.kafkaproducer.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@RequiredArgsConstructor
@Service
public class ExternalAPIService {

    private final RestTemplate restTemplate;
    private final KafkaTemplate<String, Object> kafkaTemplate;
    public static Integer staticNum = 0;
    public String fetchDataFromAPI() {
        // HTTP GET request to the API
        String API_URL = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR";
        return restTemplate.getForObject(API_URL, String.class);
    }

    @Scheduled(fixedRate = 10000)
    public void sendDataToKafka(){
        staticNum++;
        kafkaTemplate.send("my_first_topic", 2, "key1"+staticNum, fetchDataFromAPI());
    }


}
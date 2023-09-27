package com.equitytest.evelynwambui_ims_app.service_impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProductProducer {

    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;

    public void sendCSVToKafka(String csvData) {
        kafkaTemplate.send("product-csv-topic", csvData);
    }
}

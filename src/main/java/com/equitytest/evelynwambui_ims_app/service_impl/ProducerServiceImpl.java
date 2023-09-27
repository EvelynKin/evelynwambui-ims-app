package com.equitytest.evelynwambui_ims_app.service_impl;

import com.equitytest.evelynwambui_ims_app.dto.input.KafkaRequestBody;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProducerServiceImpl {
    private static final Logger logger = LoggerFactory.getLogger(ProducerServiceImpl.class);

    private final KafkaTemplate<String, String> kafkaTemplate;

    @Async
    public void sendMessage(String topic, String message) {
        logger.info(String.format("#### -> Producing message -> %s", message));
        logger.info(String.format("#### -> Getting Topic -> %s", topic));
        this.kafkaTemplate.send(topic, message);
    }
}

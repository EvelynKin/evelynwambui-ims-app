package com.equitytest.evelynwambui_ims_app.service;

import com.equitytest.evelynwambui_ims_app.dto.input.KafkaRequestBody;
import org.springframework.scheduling.annotation.Async;

public interface ProducerService {

    @Async
    void sendMessage(KafkaRequestBody kafkaRequestBody);

}

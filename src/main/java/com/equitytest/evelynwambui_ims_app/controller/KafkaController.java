package com.equitytest.evelynwambui_ims_app.controller;

import com.equitytest.evelynwambui_ims_app.dto.input.KafkaRequestBody;
import com.equitytest.evelynwambui_ims_app.service.ProducerService;
import com.equitytest.evelynwambui_ims_app.service_impl.ProducerServiceImpl;
import com.equitytest.evelynwambui_ims_app.service_impl.UserServiceImpl;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api/v1/user")
public class KafkaController {
    private final ProducerServiceImpl producerService;

    @PostMapping(value = "/publish")
    public void sendMessageToKafkaTopic(@Valid @RequestBody KafkaRequestBody kafkaRequestBody) {
        System.out.println("kafkaRequestBody is " +kafkaRequestBody);
        producerService.sendMessage(kafkaRequestBody.getTopic(), kafkaRequestBody.getMessage());
    }

}

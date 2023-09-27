package com.equitytest.evelynwambui_ims_app.dto.input;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"topic", "message"})
public class KafkaRequestBody {

    @JsonProperty("topic")
    private String topic;

    @JsonProperty("message")
    private String message;
}

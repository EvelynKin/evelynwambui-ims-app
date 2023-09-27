/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.util.HashMap;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@ToString
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"error", "responseCode", "message", "additionalProperties"})
public abstract class RequestResponse {

  @JsonProperty("error")
  private Boolean error;

  @JsonProperty("responseCode")
  private Integer responseCode;

  @JsonProperty("message")
  private String message;

  @JsonProperty("additionalProperties")
  private HashMap<String, Object> additionalProperties;
}

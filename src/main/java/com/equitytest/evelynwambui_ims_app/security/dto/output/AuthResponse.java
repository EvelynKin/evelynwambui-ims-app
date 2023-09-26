/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 25/09/2023
 * @apiNote AuthenticationResponse output DTO class
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.dto.output;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "jwtToken",
})
public class AuthResponse {

  @NotNull
  @JsonProperty("jwtToken")
  private String jwtToken;

  @NotNull @JsonProperty private String message;

  @JsonProperty private Boolean error;
}

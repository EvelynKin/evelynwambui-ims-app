/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote AuthRegistrationRequest input DTO class
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.dto.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import java.io.Serializable;
import javax.annotation.Generated;
import lombok.*;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"username", "password"})
@Generated("jsonschema2pojo")
public class SystemAuthUserRegistrationRequest implements Serializable {

  private static final long serialVersionUID = 2916126885284138846L;

  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;
}

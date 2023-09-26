/**
 * @apiNote class
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.dto.input;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import lombok.*;

import javax.annotation.Generated;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({"fullName", "username", "password", "emailAddress"})
@Generated("jsonschema2pojo")
public class UserManagementRequest {

  @JsonProperty("fullName")
  private String fullName;

  @JsonProperty("username")
  private String username;

  @JsonProperty("password")
  private String password;

  @JsonProperty("emailAddress")
  private String emailAddress;

  @JsonProperty("userRole")
  private String userRole;
}

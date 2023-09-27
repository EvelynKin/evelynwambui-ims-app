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
import jakarta.persistence.Column;
import lombok.*;

import javax.annotation.Generated;
import java.time.LocalDate;

@Getter
@Setter
@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
  "fullName",
  "username",
  "password",
  "emailAddress",
  "userRole",
  "user_description",
  "two_factor_enabled",
  "two_factor_secret",
  "admin_created_by",
  "phone_number",
  "dob"
})
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

  @JsonProperty("user_description")
  private String userDescription;

  @JsonProperty("two_factor_enabled")
  @Builder.Default
  private Boolean twoFactorEnabled = false;

  @JsonProperty("two_factor_secret")
  private String twoFactorSecret;

  @JsonProperty("admin_created_by")
  private String adminCreatedBy;

  @JsonProperty("phone_number")
  private String phoneNumber;

  @JsonProperty("dob")
  private LocalDate dateOfBirth;
}

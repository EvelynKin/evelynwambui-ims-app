/**
 * @apiNote class
 * @version 1.0.0
 * @created 26/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.entity;

import com.equitytest.evelynwambui_ims_app.domain.enum_.UserRoles;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.time.LocalDate;

@Entity
@DiscriminatorValue("REGULAR")
public class RegularUser extends User {
  @Column(name = "user_role", insertable = false, updatable = false)
  private UserRoles userRole;
  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "dob")
  private LocalDate dateOfBirth;
}

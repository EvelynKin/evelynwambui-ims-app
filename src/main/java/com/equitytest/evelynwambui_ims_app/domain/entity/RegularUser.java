/**
 * @version 1.0.0
 * @created 26/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
@Entity
@DiscriminatorValue("REGULAR")
public class RegularUser extends User {

  @Column(name = "phone_number")
  private String phoneNumber;

  @Column(name = "dob")
  private LocalDate dateOfBirth;
}

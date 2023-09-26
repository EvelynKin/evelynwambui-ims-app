/**
 * @apiNote class
 * @version 1.0.0
 * @created 26/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.entity;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Builder;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {

  @Column(name = "two_factor_enabled", nullable = false, columnDefinition = "BOOLEAN")
  @Builder.Default
  private Boolean twoFactorEnabled = false;

  @Column(name = "access_level")
  private String accessLevel;

  @Column(name = "admin_created_by")
  private String adminCreatedBy;

  private String twoFactorSecret;
}

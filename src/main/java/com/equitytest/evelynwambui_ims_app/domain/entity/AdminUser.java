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
import lombok.Builder;

@Entity
@DiscriminatorValue("ADMIN")
public class AdminUser extends User {
  @Column(name = "user_role", insertable = false, updatable = false)
  private UserRoles userRole;
  @Column(name = "access_level")
  private String accessLevel;

  @Column(name = "admin_created_by")
  private String adminCreatedBy;

  private String twoFactorSecret;
  @Column(name = "two_factor_enabled", nullable = false, columnDefinition = "BOOLEAN")
  @Builder.Default
  Boolean twoFactorEnabled = false;
}

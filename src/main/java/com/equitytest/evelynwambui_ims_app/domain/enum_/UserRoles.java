/**
 * @author David Kariuki
 * @version 1.0.0
 * @apiNote UserRole enum
 * @created 25/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.enum_;

import lombok.Getter;

@Getter
public enum UserRoles {

  SYSTEM_AUTH_USER("SAUS"), // System authentication user
  ADMIN_USER("ADUS"), // Administrator user
  REGULAR_USER("REUS"); // Ordinary user

  private final String userRole;

  UserRoles(String userRole) {
    this.userRole = userRole;
  }

  /**
   * Method to convert UserRole shortname to enum
   *
   * @param user - User - shortname
   * @return UserRole
   */
  public static UserRoles fromShortName(String user) {
    return switch (user) {
      case "SYUS" -> UserRoles.SYSTEM_AUTH_USER;
      case "ADUS" -> UserRoles.ADMIN_USER;
      case "REUS" -> UserRoles.REGULAR_USER;
      default -> throw new IllegalArgumentException("Unknown user role : " + user);
    };
  }
}

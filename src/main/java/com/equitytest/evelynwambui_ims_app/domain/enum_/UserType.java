/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote UserRole enum
 * @created 01/05/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.enum_;

import lombok.Getter;
@Getter
public enum UserType {
  SYSTEM_USER("SYSTEM"),
  ADMIN_USER("ADMIN"),
  REGULAR_USER("REGULAR");

  private final String userRole;

  UserType(String userRole) {
    this.userRole = userRole;
  }

  public static UserType fromShortName(String user) {
    return switch (user) {
      case "SYSTEM" -> UserType.SYSTEM_USER;
      case "ADMIN" -> UserType.ADMIN_USER;
      case "REGULAR" -> UserType.REGULAR_USER;
      default -> throw new IllegalArgumentException("Unknown user role: " + user);
    };
  }
}

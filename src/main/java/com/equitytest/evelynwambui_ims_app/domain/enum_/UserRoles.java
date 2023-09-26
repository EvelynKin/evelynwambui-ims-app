/**
 * @author David Kariuki
 * @version 1.0.0
 * @apiNote UserRole enum
 * @created 25/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.enum_;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Entity
@Table(name = "user_roles")
public class UserRoles {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "role_name", nullable = false, unique = true)
  private String roleName;
}

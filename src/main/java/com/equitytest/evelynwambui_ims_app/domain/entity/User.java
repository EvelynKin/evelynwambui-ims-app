/**
 * @apiNote class
 * @author David Kariuki
 * @version 1.0.0
 * @created 26/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_role", discriminatorType = DiscriminatorType.STRING)
public abstract class User {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long userId;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email_address")
  private String emailAddress;
}

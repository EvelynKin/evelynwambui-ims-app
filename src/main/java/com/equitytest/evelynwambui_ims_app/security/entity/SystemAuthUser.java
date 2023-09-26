/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote AuthUser class
 * @created 01/05/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.entity;

import com.equitytest.evelynwambui_ims_app.domain.enum_.UserRoles;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_user") // Have underscore to avoid ambiguity with postgres user table
public class SystemAuthUser implements UserDetails {

  @Id
  @GeneratedValue(
      strategy = GenerationType.AUTO) // Auto for hibernate to best select based on DB mapping
  @Column(name = "id")
  private Integer id;

  @Column(name = "username", unique = true)
  private String username;

  @Column(name = "password")
  private String password;

  @Enumerated(EnumType.STRING)
  @Column(name = "user_role")
  private UserRoles userRoles;

  @Column(name = "date_time_created")
  @Builder.Default
  private LocalDateTime dateTimeCreated = LocalDateTime.now();

  /**
   * Method to get authorities
   *
   * @return Collection<?>
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(userRoles.name()));
  }

  /**
   * Method to get username
   *
   * @return String
   */
  @Override
  public String getUsername() {
    return username;
  }

  /**
   * Method to get password
   *
   * @return String
   */
  @Override
  public String getPassword() {
    return password;
  }

  /**
   * Method to check if account is non expired
   *
   * @return boolean
   */
  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  /**
   * Method to check if account is non locked
   *
   * @return boolean
   */
  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  /**
   * Method to check if credentials are non expired
   *
   * @return boolean
   */
  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  /**
   * Method to check if user is enabled
   *
   * @return boolean
   */
  @Override
  public boolean isEnabled() {
    return true;
  }
}

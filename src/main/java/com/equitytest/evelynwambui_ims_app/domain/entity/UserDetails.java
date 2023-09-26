/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote AuthUser class
 * @created 01/05/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.entity;

import com.equitytest.evelynwambui_ims_app.domain.enum_.UserRoles;
import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "system_user")
public class UserDetails implements org.springframework.security.core.userdetails.UserDetails {

  @Id
  @Column(name = "user_id", nullable = false, unique = true, updatable = false, length = 45)
  private String userId;

  @Column(name = "full_name", nullable = false, length = 200)
  private String fullName;

  @Column(name = "username", nullable = false, length = 200)
  private String username;

  @Column(name = "email_address", nullable = false, unique = true, length = 320)
  private String emailAddress;

  @Column(name = "password", nullable = false, length = 300)
  private String password;

  @Column(name = "created_at_ts", nullable = false, length = 50)
  private LocalDateTime createdAtTimestamp;

  @Column(name = "updated_at_ts", nullable = false, length = 50)
  private LocalDateTime updatedAtTimestamp;

  @Column(name = "email_address_verified", nullable = false, columnDefinition = "BOOLEAN")
  @Builder.Default
  private Boolean emailAddressVerified = false;

  @ManyToOne
  @JoinColumn(name = "user_role_id", referencedColumnName = "id")
  private UserRoles userRoles;

  /**
   * Method to get authorities
   *
   * @return Collection<?>
   */
  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(userRoles.getRoleName()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return false;
  }

  @Override
  public boolean isAccountNonLocked() {
    return false;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return false;
  }

  @Override
  public boolean isEnabled() {
    return false;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof UserDetails that)) return false;
    return Objects.equals(userId, that.userId)
        && Objects.equals(fullName, that.fullName)
        && Objects.equals(username, that.username)
        && Objects.equals(emailAddress, that.emailAddress)
        && Objects.equals(password, that.password)
        && Objects.equals(createdAtTimestamp, that.createdAtTimestamp)
        && Objects.equals(updatedAtTimestamp, that.updatedAtTimestamp)
        && Objects.equals(emailAddressVerified, that.emailAddressVerified)
        && userRoles == that.userRoles;
  }

  @Override
  public int hashCode() {
    return Objects.hash(
        userId,
        fullName,
        username,
        emailAddress,
        password,
        createdAtTimestamp,
        updatedAtTimestamp,
        emailAddressVerified,
        userRoles);
  }
}

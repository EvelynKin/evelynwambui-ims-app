/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 26/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.domain.entity;

import com.equitytest.evelynwambui_ims_app.domain.enum_.UserType;
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
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_details")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "user_role", discriminatorType = DiscriminatorType.STRING)
public abstract class User implements UserDetails {

  @Id
  @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_id_seq")
  @SequenceGenerator(name = "user_id_seq", sequenceName = "user_id_seq", allocationSize = 1)
  @Column(name = "user_id", length = 50)
  private String userId;

  @Column(name = "full_name")
  private String fullName;

  @Column(name = "username")
  private String username;

  @Column(name = "password")
  private String password;

  @Column(name = "email_address")
  private String emailAddress;

  @Column(name = "created_at_ts", nullable = false)
  private LocalDateTime createdAtTimestamp;

  @Column(name = "updated_at_ts", nullable = false)
  private LocalDateTime updatedAtTimestamp;

  @Column(name = "email_address_verified", nullable = false, columnDefinition = "BOOLEAN")
  private Boolean emailAddressVerified = false;


  @Transient
  public UserType getUserRole() {
    if (this instanceof SystemUser) {
      return UserType.SYSTEM_USER; // You may need to adjust this based on your actual logic
    } else if (this instanceof AdminUser) {
      return UserType.ADMIN_USER; // Adjust as needed
    } else if (this instanceof RegularUser) {
      // Handle other possible subclasses or return a default value
      return UserType.REGULAR_USER;
    } else {
      throw new IllegalStateException(
          "Unsupported user subclass: " + this.getClass().getSimpleName());
    }
  }

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    return List.of(new SimpleGrantedAuthority(this.getUserRole().name()));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}

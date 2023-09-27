/**
 * @apiNote class
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service_impl;

import com.equitytest.evelynwambui_ims_app.domain.entity.AdminUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.RegularUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.SystemUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.User;
import com.equitytest.evelynwambui_ims_app.domain.enum_.UserType;
import com.equitytest.evelynwambui_ims_app.dto.input.UserManagementRequest;
import com.equitytest.evelynwambui_ims_app.dto.output.ConcreteRequestResponse;
import com.equitytest.evelynwambui_ims_app.dto.output.RequestResponse;
import com.equitytest.evelynwambui_ims_app.repository.UserRepository;
import com.equitytest.evelynwambui_ims_app.security.service_impl.JwtServiceImpl;
import com.equitytest.evelynwambui_ims_app.service.UserService;
import jakarta.validation.Valid;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtServiceImpl jwtServiceImpl;
  private final PasswordEncoder passwordEncoder;

  public RequestResponse registerUser(UserManagementRequest userManagementRequest) {

    if (userRepository.findByUsername(userManagementRequest.getUsername()).isPresent()) {

      return ConcreteRequestResponse.builder()
          .error(true)
          .responseCode(1)
          .message("User already exists with username : " + userManagementRequest.getUsername())
          .build();
    }

    User user;
    String userRole = userManagementRequest.getUserRole();

    if (UserType.SYSTEM_USER.getUserRole().equals(userRole)) {
      SystemUser systemUser = new SystemUser();
      systemUser.setUserDescription(userManagementRequest.getUserDescription());
      systemUser.setTwoFactorEnabled(userManagementRequest.getTwoFactorEnabled());
      systemUser.setTwoFactorSecret(userManagementRequest.getTwoFactorSecret());
      user = systemUser;

    } else if (UserType.ADMIN_USER.getUserRole().equalsIgnoreCase(userRole)) {
      AdminUser adminUser = new AdminUser();
      adminUser.setAdminCreatedBy(userManagementRequest.getAdminCreatedBy());
      adminUser.setUserDescription(userManagementRequest.getUserDescription());
      adminUser.setTwoFactorEnabled(userManagementRequest.getTwoFactorEnabled());
      adminUser.setTwoFactorSecret(userManagementRequest.getTwoFactorSecret());
      user = adminUser;

    } else if (UserType.REGULAR_USER.getUserRole().equalsIgnoreCase(userRole)) {
      RegularUser regularUser = new RegularUser();
      regularUser.setPhoneNumber(userManagementRequest.getPhoneNumber());
      regularUser.setDateOfBirth(userManagementRequest.getDateOfBirth());
      user = regularUser;

    } else {
      return ResponseEntity.badRequest()
          .body(
              ConcreteRequestResponse.builder()
                  .error(true)
                  .responseCode(1)
                  .message("Invalid user type : " + userRole)
                  .build())
          .getBody();
    }

    // Update the shared fields
    LocalDateTime localDateTime = LocalDateTime.now();
    user.setUserId(generateUserId());
    user.setFullName(userManagementRequest.getFullName());
    user.setUsername(userManagementRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userManagementRequest.getPassword()));
    user.setEmailAddress(userManagementRequest.getEmailAddress());
    user.setCreatedAtTimestamp(localDateTime);
    user.setUpdatedAtTimestamp(localDateTime);

    userRepository.save(user); // Save User

    HashMap<String, Object> additionalProperties = new HashMap<>();
    additionalProperties.put("jwtToken", jwtServiceImpl.generateToken(user));

    return ConcreteRequestResponse.builder()
        .error(false)
        .responseCode(0)
        .message("User registration successful!")
        .additionalProperties(additionalProperties)
        .build();
  }

  @Override
  public RequestResponse loginUser(@Valid UserManagementRequest userManagementRequest) {
    return null;
  }

  @Override
  public RequestResponse logoutUser(@Valid UserManagementRequest userManagementRequest) {
    return null;
  }

  /**
   * Method to generate userId
   *
   * @return String
   */
  private String generateUserId() {
    return "user-" + UUID.randomUUID();
  }
}

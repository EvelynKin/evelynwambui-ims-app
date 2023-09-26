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
import com.equitytest.evelynwambui_ims_app.domain.enum_.UserRole;
import com.equitytest.evelynwambui_ims_app.dto.input.UserManagementRequest;
import com.equitytest.evelynwambui_ims_app.dto.output.RequestResponse;
import com.equitytest.evelynwambui_ims_app.repository.UserRepository;
import com.equitytest.evelynwambui_ims_app.security.service_impl.JwtServiceImpl;
import com.equitytest.evelynwambui_ims_app.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final JwtServiceImpl jwtServiceImpl;
  private final PasswordEncoder passwordEncoder;

  public RequestResponse registerUser(UserManagementRequest userManagementRequest) {

    if (userRepository.findByUsername(userManagementRequest.getUsername()).isPresent()) {

      return RequestResponse.builder()
          .error(true)
          .message("User already exists with username : " + userManagementRequest.getUsername())
          .build();
    }

    User user;
    String userRole = userManagementRequest.getUserRole();

    if (UserRole.SYSTEM_USER.getUserRole().equals(userRole)) {
      user = new SystemUser();
    } else if (UserRole.ADMIN_USER.getUserRole().equalsIgnoreCase(userRole)) {
      user = new AdminUser();
    } else if (UserRole.REGULAR_USER.getUserRole().equalsIgnoreCase(userRole)) {
      user = new RegularUser();
    } else {
      return ResponseEntity.badRequest()
          .body(RequestResponse.builder().error(true).message("Invalid user type : " + userRole).build()).getBody();
    }

    LocalDateTime localDateTime = LocalDateTime.now();
    user.setUserId(generateUserId());
    user.setFullName(userManagementRequest.getFullName());
    user.setUsername(userManagementRequest.getUsername());
    user.setPassword(passwordEncoder.encode(userManagementRequest.getPassword()));
    user.setEmailAddress(userManagementRequest.getEmailAddress());
    user.setCreatedAtTimestamp(localDateTime);
    user.setUpdatedAtTimestamp(localDateTime);

    userRepository.save(user);

    var jwtToken = jwtServiceImpl.generateToken(user);
    HashMap<String, Object> additionalProperties = new HashMap<>();
    additionalProperties.put("jwtToken", jwtToken);

    return RequestResponse.builder()
        .error(false)
        .additionalProperties(additionalProperties)
        .message("User registration successful!")
        .build();
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

/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote AuthenticationServiceImpl class
 * @created 01/05/2023
 * @see com.liniantt.duesclerk.backend_api.security.service.SystemAuthService
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.service_impl;

import com.liniantt.duesclerk.backend_api.domain.enum_.UserRoles;
import com.liniantt.duesclerk.backend_api.security.dto.input.SystemAuthRequest;
import com.liniantt.duesclerk.backend_api.security.dto.input.SystemAuthUserRegistrationRequest;
import com.liniantt.duesclerk.backend_api.security.dto.output.AuthResponse;
import com.liniantt.duesclerk.backend_api.security.entity.SystemAuthUser;
import com.liniantt.duesclerk.backend_api.security.repository.SystemAuthUserRepository;
import com.liniantt.duesclerk.backend_api.security.service.SystemAuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class SystemAuthServiceImpl implements SystemAuthService {

  private final JwtServiceImpl jwtServiceImpl;
  private final PasswordEncoder passwordEncoder;
  private final AuthenticationManager authenticationManager;
  private final SystemAuthUserRepository systemAuthUserRepository;

  /**
   * Method to register Auth user
   *
   * @param systemAuthUserRegistrationRequest - request body
   * @return AuthenticationResponse
   */
  @Override
  public AuthResponse registerAuthUser(
      final @NonNull SystemAuthUserRegistrationRequest systemAuthUserRegistrationRequest) {

    if (systemAuthUserRepository
        .findByUsername(systemAuthUserRegistrationRequest.getUsername())
        .isPresent()) {

      return AuthResponse.builder()
          .error(true)
          .message(
              "User already exists with username : "
                  + systemAuthUserRegistrationRequest.getUsername())
          .build();
    }

    var authenticationUser =
        SystemAuthUser.builder()
            .username(systemAuthUserRegistrationRequest.getUsername())
            .password(passwordEncoder.encode(systemAuthUserRegistrationRequest.getPassword()))
            .userRoles(UserRoles.SYSTEM_USER)
            .build();

    systemAuthUserRepository.save(authenticationUser);

    var jwtToken = jwtServiceImpl.generateToken(authenticationUser);

    return AuthResponse.builder()
        .error(false)
        .jwtToken(jwtToken)
        .message("User registration successful!")
        .build();
  }

  /**
   * Method to authenticate AUTH user
   *
   * @param systemAuthRequest - Request body
   * @return AuthenticationResponse
   */
  @Override
  public AuthResponse authenticateAuthUser(final @NonNull SystemAuthRequest systemAuthRequest) {

    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            systemAuthRequest.getUsername(), systemAuthRequest.getPassword()));

    var authenticatedUser =
        systemAuthUserRepository
            .findByUsername(systemAuthRequest.getUsername())
            .orElseThrow(
                () ->
                    new UsernameNotFoundException(
                        "No user found with username : " + systemAuthRequest.getUsername()));

    var jwtToken = jwtServiceImpl.generateToken(authenticatedUser);

    return AuthResponse.builder()
        .error(false)
        .message("User authentication successful!")
        .jwtToken(jwtToken)
        .build();
  }

  /**
   * Method to delete authentication user
   *
   * @param systemAuthRequest - Request body
   * @return AuthenticationResponse
   */
  @Override
  public AuthResponse delete(final @NonNull SystemAuthRequest systemAuthRequest) {

    this.authenticateAuthUser(systemAuthRequest);

    systemAuthUserRepository.delete(
        systemAuthUserRepository.findByUsername(systemAuthRequest.getUsername()).get());

    return AuthResponse.builder().error(false).message("User deleted successfully!").build();
  }
}

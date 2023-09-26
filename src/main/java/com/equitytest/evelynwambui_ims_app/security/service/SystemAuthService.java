/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 25/09/2023
 * @apiNote AuthenticationService interface class

 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.service;

import com.equitytest.evelynwambui_ims_app.security.dto.input.SystemAuthRequest;
import com.equitytest.evelynwambui_ims_app.security.dto.input.SystemAuthUserRegistrationRequest;
import com.equitytest.evelynwambui_ims_app.security.dto.output.AuthResponse;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;

@Service
public interface SystemAuthService {

  /**
   * Method to register Auth user
   *
   * @param systemAuthUserRegistrationRequest - request body
   * @return AuthenticationResponse
   */
  AuthResponse registerAuthUser(
      final @NonNull SystemAuthUserRegistrationRequest systemAuthUserRegistrationRequest);

  /**
   * Method to authenticate AUTH user
   *
   * @param systemAuthRequest - Request body
   * @return AuthenticationResponse
   */
  AuthResponse authenticateAuthUser(final @NonNull SystemAuthRequest systemAuthRequest);

  /**
   * Method to delete authentication user
   *
   * @param systemAuthRequest - Request body
   * @return AuthenticationResponse
   */
  AuthResponse delete(final @NonNull SystemAuthRequest systemAuthRequest);
}

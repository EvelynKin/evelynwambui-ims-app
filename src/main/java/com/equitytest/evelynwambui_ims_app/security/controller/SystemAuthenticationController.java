/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote AuthenticationController controller class
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.controller;

import com.equitytest.evelynwambui_ims_app.security.dto.input.SystemAuthRequest;
import com.equitytest.evelynwambui_ims_app.security.dto.input.SystemAuthUserRegistrationRequest;
import com.equitytest.evelynwambui_ims_app.security.dto.output.AuthResponse;
import com.equitytest.evelynwambui_ims_app.security.service.SystemAuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@SecurityScheme(
    name = "bearerAuth",
    type = SecuritySchemeType.HTTP,
    scheme = "bearer",
    bearerFormat = "JWT")
@RequiredArgsConstructor
@RequestMapping(path = "/api/v1/auth", produces = MediaType.APPLICATION_JSON_VALUE)
public class SystemAuthenticationController {

  private final SystemAuthService systemAuthService;

  @Operation(summary = "Register authorization user")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication successful",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = SystemAuthUser.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid inputs supplied",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
        @ApiResponse(
            responseCode = "404",
            description = "Incorrect user details",
            content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
      })
  @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthResponse> register(
      @Parameter(description = "Registration request body", required = true) @RequestBody @Valid
      SystemAuthUserRegistrationRequest systemAuthUserRegistrationRequest) {

    return ResponseEntity.ok(systemAuthService.registerAuthUser(systemAuthUserRegistrationRequest));
  }

  @Operation(summary = "Authenticate authorization user")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication successful",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = AuthResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid inputs supplied",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = AuthResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Incorrect user details",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = AuthResponse.class))
            }),
      })
  @PostMapping(path = "/authenticate", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthResponse> authenticate(
      @RequestBody @Valid SystemAuthRequest systemAuthRequest) {
    return ResponseEntity.ok(systemAuthService.authenticateAuthUser(systemAuthRequest));
  }

  @Operation(summary = "Delete authorization user")
  @ApiResponses(
      value = {
        @ApiResponse(
            responseCode = "200",
            description = "Authentication successful",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = AuthResponse.class))
            }),
        @ApiResponse(
            responseCode = "400",
            description = "Invalid inputs supplied",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = AuthResponse.class))
            }),
        @ApiResponse(
            responseCode = "404",
            description = "Incorrect user details",
            content = {
              @Content(
                  mediaType = MediaType.APPLICATION_JSON_VALUE,
                  schema = @Schema(implementation = AuthResponse.class))
            })
      })
  @DeleteMapping(path = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AuthResponse> delete(
      @RequestBody @Valid SystemAuthRequest systemAuthRequest) {
    return ResponseEntity.ok(systemAuthService.delete(systemAuthRequest));
  }
}

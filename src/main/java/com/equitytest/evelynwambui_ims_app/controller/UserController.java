/**
 * @apiNote class
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @see
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.controller;

import com.equitytest.evelynwambui_ims_app.dto.input.UserManagementRequest;
import com.equitytest.evelynwambui_ims_app.dto.output.RequestResponse;
import com.equitytest.evelynwambui_ims_app.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequestMapping(value = "/api/v1/user", produces = MediaType.APPLICATION_JSON_VALUE)
@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;

  @PostMapping(value = "/login", consumes = MediaType.APPLICATION_JSON_VALUE)
  public RequestResponse loginUser(
      @RequestParam @Valid UserManagementRequest userManagementRequest) {
    return userService.loginUser(userManagementRequest);
  }

  @PostMapping(value = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public RequestResponse registerUser(
      @RequestParam @Valid UserManagementRequest userManagementRequest) {
    log.error(userManagementRequest.toString());
    return userService.registerUser(userManagementRequest);
  }

  @PostMapping(value = "/logout", consumes = MediaType.APPLICATION_JSON_VALUE)
  public RequestResponse logoutUser(
      @RequestParam @Valid UserManagementRequest userManagementRequest) {
    return userService.logoutUser(userManagementRequest);
  }

}

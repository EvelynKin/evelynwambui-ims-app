/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service;
import com.equitytest.evelynwambui_ims_app.dto.input.UserManagementRequest;
import com.equitytest.evelynwambui_ims_app.dto.output.RequestResponse;
import com.equitytest.evelynwambui_ims_app.repository.AdminUserRepository;
import com.equitytest.evelynwambui_ims_app.repository.RegularUserRepository;
import com.equitytest.evelynwambui_ims_app.repository.SystemUserRepository;
import com.equitytest.evelynwambui_ims_app.repository.UserRepository;
import com.equitytest.evelynwambui_ims_app.security.service_impl.JwtServiceImpl;
import com.equitytest.evelynwambui_ims_app.service_impl.ProducerServiceImpl;
import com.equitytest.evelynwambui_ims_app.service_impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {

  @Mock
  private UserRepository userRepository;

  @Mock
  private SystemUserRepository systemUserRepository;

  @Mock
  private AdminUserRepository adminUserRepository;

  @Mock
  private RegularUserRepository regularUserRepository;

  @Mock
  private JwtServiceImpl jwtServiceImpl;

  @Mock
  private SharedUtilitiesService sharedUtilitiesService;

  @Mock
  private PasswordEncoder passwordEncoder;

  @Mock
  private ProducerServiceImpl producerService;

  private UserServiceImpl userService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    userService = new UserServiceImpl(
        userRepository,
        systemUserRepository,
        adminUserRepository,
        regularUserRepository,
        jwtServiceImpl,
        sharedUtilitiesService,
        passwordEncoder,
        producerService
    );
  }

  @Test
  public void testRegisterUser_UserAlreadyExists() {
    // Mock data
    UserManagementRequest request = new UserManagementRequest();
    request.setUsername("existingUser");

    //when(userRepository.findByUsername("existingUser")).thenReturn(Optional.of(new User()));

    RequestResponse response = userService.registerUser(request);

    assertTrue(response.getError());
    assertEquals(1, response.getResponseCode());
    assertEquals("User already exists with username : existingUser", response.getMessage());
  }

  @Test
  public void testRegisterUser_UserCreatedSuccessfully() {
    // Mock data
    UserManagementRequest request = new UserManagementRequest();
    request.setUsername("newUser");

    when(userRepository.findByUsername("newUser")).thenReturn(Optional.empty());
    when(sharedUtilitiesService.parseToLocalDate(any())).thenReturn(null);

    RequestResponse response = userService.registerUser(request);

    assertFalse(response.getError());
    assertEquals(0, response.getResponseCode());
    assertEquals("User registration successful!", response.getMessage());
  }

  @Test
  public void testLoginUser() {
    UserManagementRequest request = new UserManagementRequest();
    request.setUsername("user");

    RequestResponse response = userService.loginUser(request);

    // Mocked behavior does not return a specific result for login
    // You can add assertions based on your actual implementation
    assertNotNull(response);
  }

  @Test
  public void testLogoutUser() {
    UserManagementRequest request = new UserManagementRequest();
    request.setUsername("user");

    RequestResponse response = userService.logoutUser(request);

    // Mocked behavior does not return a specific result for logout
    // You can add assertions based on your actual implementation
    assertNotNull(response);
  }

  @Test
  public void testGenerateUserId() {
    String userId = userService.generateUserId();

    assertNotNull(userId);
    assertTrue(userId.startsWith("user-"));
    assertTrue(userId.length() > 10); // Assuming UUIDs are at least 36 characters long
  }
}

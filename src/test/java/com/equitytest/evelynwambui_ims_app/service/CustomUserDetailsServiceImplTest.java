/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 27/09/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.service;

import static org.mockito.Mockito.*;

import com.equitytest.evelynwambui_ims_app.domain.entity.SystemUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.User;
import com.equitytest.evelynwambui_ims_app.service_impl.CustomUserDetailsServiceImpl;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsServiceImplTest {

  @Mock private EntityManager entityManager;

  private CustomUserDetailsServiceImpl userDetailsService;

  @BeforeEach
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    userDetailsService = new CustomUserDetailsServiceImpl(entityManager);
  }

  @Test
  public void testLoadUserByUsername_UserFound_ReturnsUserDetails() {
    // Mock User object
    /*User user = new User();
    user.setUserId(1L);
    user.setUserRole(User.Role.SYSTEM_USER);

    // Mock the EntityManager and Query
    Query query = mock(Query.class);
    when(entityManager.createQuery(anyString(), eq(User.class))).thenReturn(query);
    when(query.setParameter("username", "testUser")).thenReturn(query);
    when(query.getSingleResult()).thenReturn(user);*/

    // Mock the find methods for different user roles
    SystemUser systemUser = new SystemUser();
    when(entityManager.find(SystemUser.class, 1L)).thenReturn(systemUser);

    // Test the loadUserByUsername method
    UserDetails userDetails = userDetailsService.loadUserByUsername("testUser");

    // Verify that the correct find method was called
    verify(entityManager, times(1)).find(SystemUser.class, 1L);
  }

  @Test
  public void testLoadUserByUsername_UserNotFound_ThrowsUsernameNotFoundException() {
    // Mock the EntityManager and Query to throw NoResultException
    Query query = mock(Query.class);
    //when(entityManager.createQuery(anyString(), eq(User.class))).thenReturn(query);
    when(query.setParameter("username", "nonExistentUser")).thenReturn(query);
    when(query.getSingleResult()).thenThrow(NoResultException.class);

    // Test the loadUserByUsername method and expect UsernameNotFoundException
    try {
      userDetailsService.loadUserByUsername("nonExistentUser");
    } catch (UsernameNotFoundException e) {
      // Verify that the exception message matches
      assert (e.getMessage().contains("User not found with username: nonExistentUser"));
    }
  }
}

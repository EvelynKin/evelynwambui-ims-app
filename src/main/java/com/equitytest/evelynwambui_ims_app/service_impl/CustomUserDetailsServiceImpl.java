package com.equitytest.evelynwambui_ims_app.service_impl;

import com.equitytest.evelynwambui_ims_app.domain.entity.AdminUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.RegularUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.SystemUser;
import com.equitytest.evelynwambui_ims_app.domain.entity.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.NoResultException;
import jakarta.persistence.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 26/09/2023
 * @since 1.0.0
 */
@Service
public class CustomUserDetailsServiceImpl implements UserDetailsService {
  private final EntityManager entityManager;

  public CustomUserDetailsServiceImpl(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    try {
      Query query =
          entityManager.createQuery(
              "SELECT u FROM User u WHERE u.username = :username", User.class);
      query.setParameter("username", username);
      User user = (User) query.getSingleResult();

      if (user != null) {
        // Set the user role in UserDetails based on the retrieved user's role
        return switch (user.getUserRole()) {
          case SYSTEM_USER -> entityManager.find(SystemUser.class, user.getUserId());
          case ADMIN_USER -> entityManager.find(AdminUser.class, user.getUserId());
          case REGULAR_USER -> entityManager.find(RegularUser.class, user.getUserId());
          default -> throw new IllegalStateException("Unknown user role: " + user.getUserRole());
        };
      }
    } catch (NoResultException e) {
      throw new UsernameNotFoundException("User not found with username: " + username);
    }

    throw new UsernameNotFoundException("User not found with username: " + username);
  }
}

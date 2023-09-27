/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 25/09/2023
 * @apiNote ApplicationConfiguration class
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.config;

import com.equitytest.evelynwambui_ims_app.service_impl.CustomUserDetailsServiceImpl;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfiguration {

  /**
   * Bean to initialize the UserDetailsService
   *
   * @return UserDetailsService
   * @throws UsernameNotFoundException - If user not found
   */
  @Bean
  public UserDetailsService userDetailsService(EntityManager entityManager) {
    return new CustomUserDetailsServiceImpl(entityManager);
  }

  /**
   * Bean data access object responsible for fetching user details and encoding user password
   *
   * @return DaoAuthenticationProvider
   */
  @Bean
  public AuthenticationProvider authenticationProvider(EntityManager entityManager) {

    DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();

    // Set what user details service to use to get user details in case of multiple implementations
    // of the user details e.g., getting info from the database, or different profiles fetching data
    // from the in-memory database, LDAP etc.
    daoAuthenticationProvider.setUserDetailsService(userDetailsService(entityManager));

    daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());

    return daoAuthenticationProvider;
  }

  /**
   * Bean to authenticate the passed Authentication object
   *
   * @return AuthenticationManager
   * @throws Exception - exception
   */
  @Bean
  public AuthenticationManager authenticationManager(
      AuthenticationConfiguration authenticationConfiguration, EntityManager entityManager)
      throws Exception {
    DaoAuthenticationProvider authenticationProvider =
        (DaoAuthenticationProvider) authenticationProvider(entityManager);
    authenticationProvider.setUserDetailsService(userDetailsService(entityManager));

    return authenticationConfiguration.getAuthenticationManager();
  }

  /**
   * Bean for password encoding with BCryptPasswordEncoder
   *
   * @return BCryptPasswordEncoder
   */
  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptPasswordEncoder();
  }
}

/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @created 25/09/2023
 * @apiNote SecurityConfiguration class
 * @since 1.0.0
 */

package com.equitytest.evelynwambui_ims_app.config;

import com.equitytest.evelynwambui_ims_app.security.filter.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {

  private final JwtAuthenticationFilter jwtAuthenticationFilter;
  private final AuthenticationProvider authenticationProvider;

  @Bean
  public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
    httpSecurity
        .csrf()
        .disable()
        .authorizeHttpRequests()
            // Permit requests below without authentication
            .requestMatchers("/management/swagger-ui/**").permitAll() // Allow access to Swagger UI

        .requestMatchers(HttpMethod.POST, "/api/v1/auth/register", "/api/v1/auth/authenticate")
        .permitAll()
        .requestMatchers(HttpMethod.DELETE, "/api/v1/auth/delete")
        .permitAll()
        .requestMatchers(HttpMethod.POST, "/api/v1/user/**")
        .permitAll()
        .requestMatchers(HttpMethod.GET, "/api/v1/user/**")
        .permitAll()
        .requestMatchers(HttpMethod.POST, "/api/v1/product/**")
        .permitAll()
        .requestMatchers(HttpMethod.GET, "/api/v1/product/**")
        .permitAll()


        // Authenticate other requests
        .anyRequest()
        .authenticated()
        .and()
        .sessionManagement()

        // Create new session for each request
        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        .and()
        .authenticationProvider(authenticationProvider)

        // Authenticate with JWT before UsernamePasswordAuthenticationFilter for enhanced security
        .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
        .exceptionHandling()
        .authenticationEntryPoint(
            (request, response, authException) -> {
              // Handle unauthorized access
              response.setStatus(403);
            })
        .accessDeniedHandler(
            (request, response, authException) -> {
              // Hande access denied
            });

    return httpSecurity.build();
  }
}

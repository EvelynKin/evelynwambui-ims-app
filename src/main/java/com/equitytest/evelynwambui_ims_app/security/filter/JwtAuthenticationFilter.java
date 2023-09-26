/**
 * @author Evelyn Wambui
 * @version 1.0.0
 * @apiNote JwtAuthenticationFilter class
 * @created 01/05/2023
 * @since 1.0.0
 */
package com.equitytest.evelynwambui_ims_app.security.filter;

import com.equitytest.evelynwambui_ims_app.security.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

  private final JwtService jwtService;
  private final UserDetailsService userDetailsService;

  /**
   * @param httpServletRequest - HttpServletRequest
   * @param httpServletResponse - HttpServletResponse
   * @param filterChain - FilterChain
   * @throws ServletException - ServletException
   * @throws IOException - IOException
   */
  @Override
  protected void doFilterInternal(
      @NonNull HttpServletRequest httpServletRequest,
      @NonNull HttpServletResponse httpServletResponse,
      @NonNull FilterChain filterChain)
      throws ServletException, IOException {

    final String authenticationHeader = httpServletRequest.getHeader("Authorization");
    final String jwtToken;
    final String username;

    if (authenticationHeader == null || !authenticationHeader.startsWith("Bearer ")) {
      filterChain.doFilter(httpServletRequest, httpServletResponse);
      return;
    }

    jwtToken = authenticationHeader.substring(7); // get token past bearer and space string
    username = jwtService.extractUsername(jwtToken);

    // Check if user is not null and also not authenticated to prevent re-authentication
    if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

      // Get user details
      UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

      if (jwtService.isTokenValid(jwtToken, userDetails)) {

        // Create UsernamePasswordAuthenticationToken object required by SecurityContextHolder to
        // update the security context
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities());

        // Set request as token details
        authenticationToken.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(httpServletRequest));

        // Update SecurityContextHolder
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
      }
    }

    // Pass to the next filter to be executed
    filterChain.doFilter(httpServletRequest, httpServletResponse);
  }
}
